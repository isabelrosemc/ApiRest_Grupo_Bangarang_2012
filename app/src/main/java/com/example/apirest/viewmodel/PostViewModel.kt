// ================================================================================================
// Clase     : PostViewModel
// Paquete   : com.example.apirest.viewmodel
// Propósito : Mantiene el estado de la pantalla (PostUiState) y pide los posts al repositorio.
//             Sobrevive a la rotación de pantalla, por lo que no se repite la descarga.
// ================================================================================================
package com.example.apirest.viewmodel  // Paquete del ViewModel


import androidx.lifecycle.ViewModel                   // Clase base ViewModel
import androidx.lifecycle.viewModelScope              // Alcance de corrutinas del VM
import com.example.apirest.repository.PostRepository  // Fuente de datos
import kotlinx.coroutines.flow.MutableStateFlow       // Flujo de estado modificable
import kotlinx.coroutines.flow.StateFlow              // Flujo de estado de solo lectura
import kotlinx.coroutines.flow.asStateFlow            // Convierte a solo lectura
import kotlinx.coroutines.launch                      // Lanza una corrutina


class PostViewModel : ViewModel() {  // Hereda de ViewModel


    private val repository = PostRepository()                     // Repositorio de posts
    private val _uiState = MutableStateFlow(PostUiState())        // Estado interno (modificable)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()  // Estado público (solo lectura)


    init {            // Se ejecuta al crear el VM
        fetchPosts()  // Carga inicial de datos
    }


    // --------------------------------------------------------------------------------------------
    // Función   : fetchPosts
    // Propósito : Descarga los posts en segundo plano y actualiza el estado según el resultado.
    //             Es pública para que la pantalla pueda reintentar si ocurre un error.
    // --------------------------------------------------------------------------------------------
    fun fetchPosts() {                                                      // Obtiene los posts
        viewModelScope.launch {                                             // Corrutina en el VM
            _uiState.value = PostUiState(isLoading = true)                  // Estado: cargando
            try {                                                           // Intenta la petición
                val posts = repository.getPosts()                           // Llamada a la API
                _uiState.value = PostUiState(posts = posts)                 // Estado: datos listos
            } catch (e: Exception) {                                        // Sin red o timeout
                _uiState.value = PostUiState(errorMessage = buildError(e))  // Estado: error
            }
        }
    }


    // --------------------------------------------------------------------------------------------
    // Función   : buildError
    // Propósito : Construye un mensaje legible a partir de la excepción recibida.
    // --------------------------------------------------------------------------------------------
    private fun buildError(e: Exception): String {         // Función auxiliar privada
        val detail = e.message ?: "error desconocido"      // Detalle o texto por defecto
        return "No se pudieron cargar los posts: $detail"  // Mensaje para el usuario
    }
}
