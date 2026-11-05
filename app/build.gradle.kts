// ================================================================================================
// Archivo   : app/build.gradle.kts (módulo app)
// Propósito : Configura la compilación de la app y declara todas sus librerías (dependencias).
// ================================================================================================
import org.jetbrains.kotlin.gradle.dsl.JvmTarget  // Tipo para indicar la versión de Java


plugins {                                      // Plugins aplicados a este módulo
    id("com.android.application")              // Compila una app Android
    id("org.jetbrains.kotlin.android")         // Habilita Kotlin
    id("org.jetbrains.kotlin.plugin.compose")  // Habilita Jetpack Compose
}


android {                              // Configuración Android del módulo
    namespace = "com.example.apirest"  // Paquete base del código (clase R)
    compileSdk = 36                    // API con la que se compila


    defaultConfig {                            // Datos generales de la app
        applicationId = "com.example.apirest"  // Identificador único de la app
        minSdk = 24                            // Android 7.0 como mínimo
        targetSdk = 36                         // API objetivo de la app
        versionCode = 1                        // Versión interna (entero)
        versionName = "1.0"                    // Versión visible al usuario
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {                                                          // Tipos de compilación
        release {                                                         // Para publicar
            isMinifyEnabled = false                                       // Sin ofuscar código
            proguardFiles(                                                // Reglas de ProGuard/R8
                getDefaultProguardFile("proguard-android-optimize.txt"),  // Reglas por defecto
                "proguard-rules.pro"                                      // Reglas propias
            )
        }
    }


    compileOptions {                                  // Versión de Java del código
        sourceCompatibility = JavaVersion.VERSION_11  // Código fuente Java 11
        targetCompatibility = JavaVersion.VERSION_11  // Bytecode Java 11
    }


    buildFeatures {     // Funciones extra del módulo
        compose = true  // Activa Jetpack Compose
    }
}


kotlin {                                 // Configuración del compilador Kotlin
    compilerOptions {                    // Opciones del compilador
        jvmTarget.set(JvmTarget.JVM_11)  // Mismo destino que Java (11)
    }
}


dependencies {  // Librerías que usa la app
    // ---------------------------------------------------------------- AndroidX y Jetpack Compose
    implementation("androidx.core:core-ktx:1.16.0")                      // Extensiones Kotlin
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")     // Ciclo de vida
    implementation("androidx.activity:activity-compose:1.10.1")          // setContent()
    implementation(platform("androidx.compose:compose-bom:2025.08.00"))  // Versiones Compose
    implementation("androidx.compose.ui:ui")                             // Núcleo de Compose
    implementation("androidx.compose.ui:ui-graphics")                    // Gráficos de Compose
    implementation("androidx.compose.ui:ui-tooling-preview")             // Vista previa
    implementation("androidx.compose.material3:material3")               // Componentes Material 3


    // ---------------------------------------------------------------- ViewModel para Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")  // viewModel()


    // ---------------------------------------------------------------- Retrofit + Gson (API REST)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")        // Cliente HTTP
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")  // JSON <-> Kotlin


    // ---------------------------------------------------------------- Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")  // Asincronía


    // ---------------------------------------------------------------- Pruebas (plantilla)
    testImplementation("junit:junit:4.13.2")                                 // Pruebas unitarias
    androidTestImplementation("androidx.test.ext:junit:1.3.0")               // Pruebas en equipo
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")  // Espresso
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")          // Pruebas de UI
    debugImplementation("androidx.compose.ui:ui-tooling")                    // Inspector de UI
    debugImplementation("androidx.compose.ui:ui-test-manifest")              // Manifest de pruebas
}
