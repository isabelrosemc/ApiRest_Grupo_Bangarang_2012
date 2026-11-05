// ================================================================================================
// Clase     : PostUiState
// Paquete   : com.example.apirest.viewmodel
// Propósito : Representa todo lo que la pantalla necesita mostrar: carga, datos o error.
// ================================================================================================
package com.example.apirest.viewmodel  // Paquete del ViewModel


import com.example.apirest.data.model.Post  // Modelo de datos


data class PostUiState(                   // Estado de la pantalla
    val isLoading: Boolean = false,       // true mientras se descarga
    val posts: List<Post> = emptyList(),  // Posts recibidos
    val errorMessage: String? = null      // Mensaje de error (o null)
)
