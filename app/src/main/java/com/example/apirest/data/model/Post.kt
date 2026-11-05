// ================================================================================================
// Clase     : Post
// Paquete   : com.example.apirest.data.model
// Propósito : Modelo de datos de un post. Los nombres de las propiedades deben coincidir
//             exactamente con las claves del JSON para que Gson pueda convertirlos.
// ================================================================================================
package com.example.apirest.data.model  // Paquete del modelo


data class Post(        // Clase de datos inmutable
    val userId: Int,    // ID del usuario autor del post
    val id: Int,        // ID único del post
    val title: String,  // Título del post
    val body: String    // Cuerpo o contenido del post
)
