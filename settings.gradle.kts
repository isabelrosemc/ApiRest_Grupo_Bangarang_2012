// ================================================================================================
// Archivo   : settings.gradle.kts (raíz del proyecto)
// Propósito : Declara los repositorios de descarga, el nombre del proyecto y sus módulos.
// ================================================================================================
pluginManagement {                                      // Repositorios para descargar plugins
    repositories {                                      // Lista de repositorios de plugins
        google {                                        // Repositorio oficial de Google
            content {                                   // Filtra qué grupos se buscan aquí
                includeGroupByRegex("com\\.android.*")  // Plugins y herramientas Android
                includeGroupByRegex("com\\.google.*")   // Librerías de Google
                includeGroupByRegex("androidx.*")       // Librerías AndroidX
            }
        }
        mavenCentral()                                  // Repositorio Maven Central
        gradlePluginPortal()                            // Portal oficial de plugins Gradle
    }
}


dependencyResolutionManagement {                                  // Repositorios para las librerías
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)  // Repositorios centralizados
    repositories {                                                // Repositorios de librerías
        google()                                                  // Librerías AndroidX y Google
        mavenCentral()                                            // Retrofit, Gson, corrutinas
    }
}


rootProject.name = "ApiRest_GrupoX"  // Nombre visible del proyecto
include(":app")                      // Incluye el módulo de la app
