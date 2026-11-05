// ================================================================================================
// Interfaz  : ApiService
// Paquete   : com.example.apirest.data.remote
// Propósito : Declara los endpoints HTTP de la API. Retrofit genera la implementación.
// ================================================================================================
package com.example.apirest.data.remote  // Paquete de acceso remoto


import com.example.apirest.data.model.Post  // Modelo que devuelve la API
import retrofit2.http.GET                   // Anotación para peticiones GET


interface ApiService {  // Contrato de la API REST


    // --------------------------------------------------------------------------------------------
    // Función   : getPosts
    // Propósito : GET https://jsonplaceholder.typicode.com/posts -> lista de 100 posts.
    // --------------------------------------------------------------------------------------------
    @GET("posts")                       // Ruta relativa a la URL base
    suspend fun getPosts(): List<Post>  // suspend: se ejecuta en corrutina
}
