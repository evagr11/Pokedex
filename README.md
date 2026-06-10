<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A0F3D2E%2C50%3A10B981%2C100%3AD1FAE5&height=200&section=header&text=Pokedex&fontSize=60&fontColor=fff&fontAlignY=38&desc=Tu+enciclopedia+Pok%C3%A9mon+para+Android&descAlignY=62&descSize=18" width="100%"/>
</div>

<div align="center">
  <a href="https://github.com/evagr11/Pokedex" target="_blank"><img src="https://img.shields.io/badge/Ver_Código-100000?style=for-the-badge&logo=github&logoColor=white" alt="Ver Código"/></a>
  <a href="https://evagr11.github.io/Curriculum/proyectos/pokedex.html" target="_blank"><img src="https://img.shields.io/badge/Ver_Web-43B581?style=for-the-badge&logo=googlechrome&logoColor=white" alt="Ver Web"/></a>
</div>

<br>

<div align="center">
  <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com/?font=JetBrains+Mono&size=18&duration=3500&pause=800&color=10B981&center=true&vCenter=true&width=600&lines=Aplicaci%C3%B3n+Android+nativa+con+Java%3BConsumo+eficiente+de+la+Pok%C3%A9API%3BInterfaz+con+RecyclerView+y+Glide%3BB%C3%BAsqueda+y+filtrado+de+Pok%C3%A9mon%3BArquitectura+MVC+simple" alt="Typing SVG" /></a>
</div>

# 🚀 Sobre el Proyecto

Este proyecto es una **aplicación nativa de Android** que implementa una completa Pokédex. La aplicación consume la [PokéAPI v2](https://pokeapi.co/) para obtener y mostrar datos detallados de más de 1000 Pokémon. Cuenta con una pantalla de inicio que gestiona la carga de datos, una vista principal con un listado de todos los Pokémon y funcionalidad de búsqueda, y una pantalla de detalle para cada criatura.

La interfaz está diseñada para ser intuitiva y visualmente atractiva, utilizando `RecyclerView` para un desplazamiento eficiente y `Glide` para la carga asíncrona de imágenes desde la red.

<!-- 💡 Sugerencia: añade aquí una captura de la pantalla principal de la Pokédex. -->

### ✨ Características principales:

*   **📖 Listado Completo:** Muestra todos los Pokémon disponibles en la API, cargados en lotes para optimizar el rendimiento.
*   **🔍 Búsqueda Dinámica:** Permite buscar Pokémon por **nombre** o por su **número** en la Pokédex.
*   **ℹ️ Vista de Detalle:** Al seleccionar un Pokémon, se muestra una pantalla con su imagen, nombre, tipos, peso, altura y una breve descripción de su historia.
*   **🎨 Diseño Temático:** El color de fondo de la pantalla de detalle cambia dinámicamente para coincidir con el tipo principal del Pokémon.
*   **⏳ Pantalla de Carga:** Una pantalla de bienvenida (`LandingActivity`) gestiona la descarga inicial de datos, asegurando que la aplicación esté lista para usar.

<!-- 💡 Sugerencia: añade aquí un gif o capturas mostrando la búsqueda y la vista de detalle de un Pokémon. -->

# 🧠 Contexto del Proyecto

Este es un **proyecto educativo** desarrollado como parte de las asignaturas de Diseño de interfaces y Programación de dispositivos multimedia. El objetivo era crear una aplicación funcional y bien estructurada, aplicando conceptos como consumo de APIs REST, gestión de ciclo de vida de actividades, y diseño de interfaces de usuario complejas con `RecyclerView`.

# 🛠️ Stack Tecnológico

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/PokéAPI-EF5350?style=for-the-badge&logo=pokemon&logoColor=white" alt="PokeAPI"/>
  <img src="https://img.shields.io/badge/Glide-00BFFF?style=for-the-badge&logo=glide&logoColor=white" alt="Glide"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle"/>
  <img src="https://img.shields.io/badge/Status-Estable-brightgreen?style=for-the-badge" alt="Status Estable"/>
</div>

*   **Lenguaje:** Java 11
*   **Plataforma:** Android (minSDK 24)
*   **Librerías Principales:**
    *   `AndroidX (AppCompat, ConstraintLayout, RecyclerView)`: Componentes fundamentales para la interfaz de usuario.
    *   `Material Components`: Para elementos de diseño visual moderno.
    *   `Volley`: Para realizar peticiones HTTP a la PokéAPI.
    *   `Glide`: Para la carga y caché eficiente de imágenes.
*   **API Externa:** PokéAPI v2
*   **Sistema de Build:** Gradle

# 📂 Estructura del Proyecto

El proyecto sigue una arquitectura MVC (Modelo-Vista-Controlador) simple para separar las responsabilidades.

```bash
Pokedex-main/app/src/main/java/com/example/pokedex/
├── Controller/
│   └── API.java          # Gestiona las llamadas a la PokéAPI con Volley.
├── Model/
│   ├── Pokemon.java      # Clase POJO para representar un Pokémon.
│   └── TipoPokemonEnum.java # Enum para gestionar tipos y colores.
└── View/
    ├── LandingActivity.java  # Pantalla de carga inicial.
    ├── GeneralPokedex.java   # Vista principal con el listado y búsqueda.
    ├── InfoPokemon.java      # Pantalla de detalle del Pokémon.
    └── PokemonAdapter.java   # Adaptador para el RecyclerView.
```

# 🚀 Cómo Utilizar el Proyecto

Para ejecutar este proyecto, necesitarás **Android Studio**.

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/evagr11/Pokedex.git
    ```

2.  **Abre el proyecto en Android Studio:**
    *   Abre Android Studio.
    *   Selecciona `File` > `Open...` y navega hasta la carpeta `Pokedex-main` que acabas de clonar.
    *   Espera a que Gradle sincronice todas las dependencias del proyecto. Este proceso puede tardar unos minutos.

3.  **Ejecuta la aplicación:**
    *   Selecciona un emulador de Android o conecta un dispositivo físico.
    *   Haz clic en el botón `Run 'app'` (el icono de play verde) o usa el atajo `Shift + F10`.

¡La aplicación se compilará y se instalará en el dispositivo seleccionado!

<div align="center">
  <p>Creado por <strong>Eva Gallardo</strong></p>
  <a href="https://github.com/evagr11" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  </a>
  <a href="https://www.linkedin.com/in/eva-gallardo-romero-830362349/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</div>

<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A0F3D2E%2C50%3A10B981%2C100%3AD1FAE5&height=120&section=footer" width="100%"/>
</div>
