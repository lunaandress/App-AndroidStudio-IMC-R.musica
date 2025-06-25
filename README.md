# ActivityIMC

Aplicación Android que calcula el Índice de Masa Corporal (IMC) y ofrece navegación mediante fragmentos, incluyendo un reproductor de música integrado.

---

## Descripción

Esta app permite a los usuarios:

- Ingresar datos personales como peso, edad y altura.
- Calcular el IMC y visualizar resultados con mensajes personalizados.
- Navegar entre diferentes secciones usando pestañas y fragmentos.
- Reproducir música con controles básicos (play, pausa, stop, siguiente, anterior).
- Visualizar información dinámica de las canciones y la carátula.

---

## Tecnologías

- Java
- Android SDK
- Material Design Components (FloatingActionButton, CardView, RangeSlider)
- MediaPlayer para audio
- Fragmentos para UI modular y navegación

---

## Estructura de la App

- **MainActivity:** Pantalla principal donde el usuario ingresa datos y calcula IMC.
- **Activity2Fragments:** Actividad que contiene y controla la navegación entre fragmentos.
- **Fragment1:** Muestra el resultado del IMC con interpretación y descripción.
- **Fragment2, Fragment3:** Otros fragmentos (a definir según necesidad).
- **Fragment4:** Reproductor de música con controles y lista dinámica.

---

## Funcionalidades destacadas

- **Cálculo IMC:** A partir del peso y altura ingresados, calcula el índice y muestra el estado de salud.
- **Navegación por pestañas:** Permite cambiar de fragmento y reproduce un sonido al cambiar.
- **Reproductor de música:**
  - Lista dinámica de canciones con título y carátula.
  - Controles: play, pause, stop, siguiente, anterior.
  - Barra de progreso (SeekBar) sincronizada con la reproducción.
  - Actualización de tiempo transcurrido y duración total.

---

## Uso

1. En `MainActivity`, ajusta peso, edad y altura usando los botones y sliders.
2. Pulsa "Calcular" para obtener el IMC y ver el resultado en `Fragment1`.
3. Navega entre pestañas para explorar otros fragmentos.
4. En el fragmento de música (`Fragment4`), selecciona canciones y controla la reproducción.

---

## Archivos importantes

- `MainActivity.java` — Lógica para entrada de datos y cálculo IMC.
- `Activity2Fragments.java` — Manejo de fragmentos y navegación.
- `Fragment1.java` — Visualización de resultados IMC.
- `Fragment4.java` — Reproductor de música.
- Recursos en `res/raw/` para canciones y `res/drawable/` para imágenes.

---

## Notas

- La app utiliza `MediaPlayer`, asegúrate de manejar bien su ciclo para evitar fugas de memoria.
- El diseño puede adaptarse y extenderse con más fragmentos según requerimientos.

---

## Licencia

Este proyecto es solo para fines educativos y demostrativos.

---

¿Preguntas o mejoras? ¡No dudes en contactarme!
