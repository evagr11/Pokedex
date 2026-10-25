<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A7F1D1D%2C50%3ADC2626%2C100%3AFCA5A5&height=200&section=header&text=Pokedex&fontSize=60&fontColor=fff&fontAlignY=38&desc=Tu+enciclopedia+Pok%C3%A9mon+para+Android&descAlignY=62&descSize=18" width="100%"/>
</div>

<div align="center">
  <a href="https://github.com/evagr11/Pokedex" target="_blank"><img src="https://img.shields.io/badge/Ver_Código-7F1D1D?style=for-the-badge&logo=github&logoColor=white" alt="Ver Código"/></a>
  <a href="https://evagr11.github.io/Curriculum/proyectos/pokedex.html" target="_blank"><img src="https://img.shields.io/badge/Ver_Web-DC2626?style=for-the-badge&logo=googlechrome&logoColor=white" alt="Ver Web"/></a>
</div>

<br>

<div align="center">
  <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com/?font=JetBrains+Mono&size=18&duration=3500&pause=800&color=DC2626&center=true&vCenter=true&width=600&lines=Aplicaci%C3%B3n+Android+nativa+con+Java%3B1028+Pok%C3%A9mon+desde+la+Pok%C3%A9API%3BRecyclerView+en+cuadr%C3%ADcula+y+Glide%3BB%C3%BAsqueda+por+nombre+o+n%C3%BAmero%3BArquitectura+MVC+simple" alt="Typing SVG" /></a>
</div>

# 🚀 Sobre el Proyecto

Este proyecto es una **aplicación nativa de Android** que implementa una completa Pokédex. La aplicación consume la [PokéAPI v2](https://pokeapi.co/) para obtener y mostrar datos detallados de **1028 Pokémon**, con las descripciones de la Pokédex **en español**. Cuenta con una pantalla de inicio que gestiona la carga de datos, una vista principal con un listado de todos los Pokémon y funcionalidad de búsqueda, y una pantalla de detalle para cada criatura.

La interfaz está diseñada para ser intuitiva y visualmente atractiva, utilizando `RecyclerView` para un desplazamiento eficiente y `Glide` para la carga asíncrona de imágenes desde la red.

### ✨ Características principales:

*   **📖 Listado Completo:** Cuadrícula de 3 columnas con el arte oficial de cada Pokémon. Se van mostrando por lotes a medida que haces scroll (*scroll infinito*) para que la lista sea fluida.
*   **🔍 Búsqueda Dinámica:** Filtra mientras escribes, por **nombre** o por **número** de la Pokédex.
*   **ℹ️ Vista de Detalle:** Al seleccionar un Pokémon, se muestra una pantalla con su imagen, nombre, tipos, peso, altura y su descripción de la Pokédex en español.
*   **🎨 Diseño Temático:** Los **18 tipos** tienen su icono y su color propios (definidos en `TipoPokemonEnum`), y el fondo de la pantalla de detalle toma el color del tipo principal.
*   **⏳ Pantalla de Carga:** Una pantalla de bienvenida (`LandingActivity`) descarga todos los datos al abrir la app con **Volley** (la ficha de cada Pokémon y su especie) y no entra en la Pokédex hasta que termina, así la búsqueda funciona sobre la lista completa.

# 🧠 Contexto del Proyecto

Este es un **proyecto educativo** desarrollado como parte de las asignaturas de **Desarrollo de Interfaces** y **Programación Multimedia y Dispositivos Móviles** del ciclo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**. El objetivo era crear una aplicación funcional y bien estructurada, aplicando conceptos como consumo de APIs REST, gestión de ciclo de vida de actividades, y diseño de interfaces de usuario complejas con `RecyclerView`.

# 🛠️ Stack Tecnológico

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/PokéAPI-EF5350?style=for-the-badge&logo=pokemon&logoColor=white" alt="PokeAPI"/>
  <img src="https://img.shields.io/badge/Glide-00BFFF?style=for-the-badge&logo=glide&logoColor=white" alt="Glide"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle"/>
  <img src="https://img.shields.io/badge/Status-Estable-brightgreen?style=for-the-badge" alt="Status Estable"/>
</div>

<br>

<div align="center">

| Área | Tecnología |
| :--- | :--------- |
| **Lenguaje** | `Java 11` |
| **Plataforma** | `Android` (minSdk 24 · targetSdk 36) |
| **Interfaz** | `RecyclerView` con `GridLayoutManager`, `SearchView`, `ConstraintLayout`, `Material Components` |
| **Red** | `Volley` para las peticiones a la PokéAPI |
| **Imágenes** | `Glide` para cargar y guardar en caché el arte oficial |
| **API externa** | `PokéAPI v2` (`/pokemon` y `/pokemon-species`) |
| **Build** | `Gradle` (Kotlin DSL) |

</div>

# 📂 Estructura del Proyecto

El proyecto sigue una arquitectura MVC (Modelo-Vista-Controlador) simple para separar las responsabilidades.

```bash
Pokedex/app/src/main/java/com/example/pokedex/
├── Controller/
│   └── API.java          # Gestiona las llamadas a la PokéAPI con Volley.
├── Model/
│   ├── Pokemon.java      # Clase POJO para representar un Pokémon.
│   └── TipoPokemonEnum.java # Los 18 tipos con su icono y su color.
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
    *   Selecciona `File` > `Open...` y navega hasta la carpeta `Pokedex` que acabas de clonar.
    *   Espera a que Gradle sincronice todas las dependencias del proyecto. Este proceso puede tardar unos minutos.

3.  **Ejecuta la aplicación:**
    *   Selecciona un emulador de Android o conecta un dispositivo físico.
    *   Haz clic en el botón `Run 'app'` (el icono de play verde) o usa el atajo `Shift + F10`.

¡La aplicación se compilará y se instalará en el dispositivo seleccionado!

> ⏳ La primera pantalla tarda un poco: descarga los datos de los 1028 Pokémon antes de entrar. Hace falta conexión a internet.

<br>

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
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0%3A7F1D1D%2C50%3ADC2626%2C100%3AFCA5A5&height=120&section=footer" width="100%"/>
</div>
