# MyApplication

Este proyecto lo desarrollé como parte de mi proceso de aprendizaje en programación
de aplicaciones móviles con Android. Actualmente curso el sexto semestre de
Ingeniería de Software y Datos, y esta app nació de una actividad práctica del curso
de Programación de Dispositivos Móviles, enfocada en entender cómo se comunican dos
pantallas (actividades) dentro de una misma aplicación Android.

Mi idea al construirla no fue solo cumplir con el ejercicio base, sino aprovecharla
para practicar buenas prácticas de diseño de interfaz y estructurar el código de una
forma un poco más cercana a como se vería una app real, aunque todavía estoy en
proceso de aprender los patrones más avanzados de arquitectura Android (como MVVM),
que espero ir incorporando en próximos proyectos.

## ¿Qué hace la app?

Cuando se abre la aplicación, lo primero que ve el usuario es una pantalla de
bienvenida que lo saluda según la hora del día ("Buenos días", "Buenas tardes" o
"Buenas noches"), calculado a partir de la hora del dispositivo con `Calendar`. Me
pareció un buen ejercicio para practicar lógica condicional simple combinada con
componentes visuales, en vez de dejar la pantalla de inicio como un formulario vacío.

Debajo del saludo, la app muestra un listado de actividades posibles. Por ahora solo
una está habilitada: **Enviar mensaje**. Las demás (Notificaciones, Mi perfil,
Configuración) las dejé visibles pero deshabilitadas, marcadas con una etiqueta de
"Próximamente", pensando en que a futuro pueda seguir ampliando la app con más
funciones sin tener que rediseñar la pantalla principal desde cero.

Al entrar a "Enviar mensaje", el usuario puede escribir un texto y enviarlo a una
segunda pantalla, que muestra el mensaje recibido y le da la opción de responder
"Recibido" o "Cancelado". Ese resultado regresa a la pantalla anterior y se muestra
en una tarjeta de estado. Esta parte es la que corresponde al ejercicio original del
curso: practicar el paso de datos entre actividades usando `Intent` y
`registerForActivityResult`, en lugar del método antiguo `startActivityForResult`
que ya está obsoleto.

## Lo que aprendí construyéndola

- A pasar información entre dos actividades en ambas direcciones (ida con `Intent`,
  vuelta con `setResult` y un `ActivityResultLauncher`).
- A separar la lógica de cada pantalla en su propia clase (`MainActivity`,
  `EnviarMensajeActivity`, `Activity2`) en vez de amontonar todo en una sola
  actividad.
- A usar componentes de Material Design 3 (`MaterialCardView`, `MaterialButton`,
  `TextInputLayout`) para que la interfaz se vea más cuidada que con las vistas
  básicas de Android.
- A crear íconos vectoriales propios (`VectorDrawable`) en vez de depender solo de
  los íconos genéricos que trae el sistema.
- A manejar estados visuales distintos para un mismo tipo de componente (tarjetas
  "activas" vs. "deshabilitadas") jugando con colores y opacidad en vez de
  ocultar elementos.

## Tecnologías utilizadas

- Kotlin
- Android Views con `ConstraintLayout`
- Material Components 3
- Gradle (Kotlin DSL)

## Estructura del proyecto

app/src/main/java/com/example/myapplication/
├── MainActivity.kt # Pantalla de bienvenida y menú de actividades
├── EnviarMensajeActivity.kt # Pantalla para escribir y enviar el mensaje
└── Activity2.kt # Pantalla que recibe el mensaje y responde

app/src/main/res/layout/
├── activity_main.xml
├── activity_enviar_mensaje.xml
└── activity_2.xml

## Cómo ejecutarla

1. Clonar el repositorio.
2. Abrir la carpeta en Android Studio.
3. Esperar a que termine el Gradle Sync.
4. Ejecutar en un emulador o dispositivo físico con Android 7.0 (API 24) o superior.

## Estado del proyecto

Sigue siendo un ejercicio académico en evolución. La idea es seguir practicando
sobre esta misma base a medida que avance en el curso y vaya aprendiendo conceptos
nuevos (persistencia de datos, Jetpack Compose, arquitectura por capas, etc.).

## Licencia

Todos los derechos reservados. Ver [LICENSE](LICENSE).
