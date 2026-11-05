// ================================================================================================
// Clase     : PostRepository
// Paquete   : com.example.apirest.repository
// Propósito : Única puerta de acceso a los datos. El ViewModel no sabe si vienen de la red
//             o de otra fuente; solo le pide los posts al repositorio.
// ================================================================================================
package com.example.apirest.repository  // Paquete del repositorio


import com.example.apirest.data.model.Post               // Modelo de datos
import com.example.apirest.data.remote.RetrofitInstance  // Acceso a la API


class PostRepository {  // Repositorio de posts


    private val api = RetrofitInstance.api  // Servicio de la API REST


    // --------------------------------------------------------------------------------------------
    // Función   : getPosts
    // Propósito : Solicita la lista de posts a la API. Puede lanzar excepción si no hay red.
    // Retorna   : List<Post> con los posts recibidos.
    // --------------------------------------------------------------------------------------------
    suspend fun getPosts(): List<Post> {  // Función suspendida
        return api.getPosts()             // Llama al endpoint GET /posts
    }
}
