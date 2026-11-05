// ================================================================================================
// Clase     : MainActivity
// Paquete   : com.example.apirest
// Propósito : Punto de entrada de la app. Activa edge-to-edge, aplica el tema y abre PostScreen.
// ================================================================================================
package com.example.apirest  // Paquete raíz de la app


import android.os.Bundle                               // Estado guardado de la actividad
import androidx.activity.ComponentActivity             // Actividad base para Compose
import androidx.activity.compose.setContent            // Define la UI con Compose
import androidx.activity.enableEdgeToEdge              // Dibuja bajo las barras
import androidx.lifecycle.viewmodel.compose.viewModel  // Crea u obtiene el ViewModel
import com.example.apirest.ui.screens.PostScreen       // Pantalla principal
import com.example.apirest.ui.theme.ApiRestTheme       // Tema de la app
import com.example.apirest.viewmodel.PostViewModel     // ViewModel de posts


class MainActivity : ComponentActivity() {  // Actividad principal


    // --------------------------------------------------------------------------------------------
    // Función   : onCreate
    // Propósito : Se ejecuta al abrir la actividad; aquí se construye toda la interfaz.
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {        // Inicio de la actividad
        super.onCreate(savedInstanceState)                      // Llama a la clase padre
        enableEdgeToEdge()                                      // Contenido bajo las barras
        setContent {                                            // Inicia Jetpack Compose
            ApiRestTheme {                                      // Aplica el tema Material 3
                val postViewModel: PostViewModel = viewModel()  // Obtiene el ViewModel
                PostScreen(viewModel = postViewModel)           // Muestra la pantalla
            }
        }
    }
}
