package com.example.calculadorabcv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var tasaBCV: Double = 0.0
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtTasa = findViewById<TextView>(R.id.txtTasa)
        val inputMonto = findViewById<EditText>(R.id.inputMonto)
        val btnConvertir = findViewById<Button>(R.id.btnConvertir)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        // Consultar automáticamente la tasa al abrir la app
        obtenerTasaBCV(txtTasa)

        // Conversión al presionar el botón
        btnConvertir.setOnClickListener {
            val monto = inputMonto.text.toString().toDoubleOrNull()
            if (monto != null && tasaBCV > 0) {
                val enDolares = monto / tasaBCV
                val enBolivares = monto * tasaBCV
                txtResultado.text = "USD: %.2f | Bs: %.2f".format(enDolares, enBolivares)
            } else {
                txtResultado.text = "Ingresa un monto válido"
            }
        }
    }

    private fun obtenerTasaBCV(txtTasa: TextView) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url("https://ve.dolarapi.com/v1/dolares/oficial")
                    .build()

                val response = client.newCall(request).execute()
                val jsonData = response.body?.string()

                Log.d("MainActivity", "JSON recibido: $jsonData")

                jsonData?.let {
                    val jsonObject = JSONObject(it)
                    tasaBCV = jsonObject.optDouble("precio", 0.0)
                }

                withContext(Dispatchers.Main) {
                    if (tasaBCV > 0) {
                        txtTasa.text = "Tasa BCV: %.2f Bs/USD".format(tasaBCV)
                    } else {
                        txtTasa.text = "No se pudo obtener la tasa"
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    txtTasa.text = "Error al obtener tasa: ${e.localizedMessage ?: "sin detalles"}"
                }
            }
        }
    }
}