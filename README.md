# 🧮 BCVCalculadora (Proceso de Prueba)

**BCVCalculadora** es un microservicio backend desarrollado en **Kotlin** que automatiza la consulta de tasas oficiales del Banco Central de Venezuela (BCV). Está diseñado para integrarse fácilmente en aplicaciones móviles o sistemas backend que requieran tasas actualizadas de forma confiable.

## 🚀 Características
- Consulta automática de tasas oficiales (USD, EUR, etc.) desde el sitio del BCV.
- Implementación con **OkHttp** y **coroutines** para alto rendimiento.
- Manejo robusto de errores y validación de respuestas HTML.
- Arquitectura modular y mantenible.
- Listo para integrarse en apps Android o servicios backend.

## 📦 Tecnologías
- Kotlin
- OkHttp
- Coroutines
- Jsoup (para parseo HTML)

## 📁 Estructura
- `src/main/kotlin`: lógica principal del servicio.
- `src/test`: pruebas unitarias.
- `build.gradle.kts`: configuración del proyecto.

## 🛠️ Uso
Puedes ejecutar el servicio localmente o integrarlo como módulo en tu aplicación. También puedes adaptarlo para exponer un endpoint REST si lo deseas.

---

Este proyecto forma parte de un ecosistema de herramientas backend enfocadas en eficiencia, seguridad y automatización.
