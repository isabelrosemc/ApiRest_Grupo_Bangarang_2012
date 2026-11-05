// ================================================================================================
// Objeto    : RetrofitInstance
// Paquete   : com.example.apirest.data.remote
// Propósito : Singleton que configura Retrofit una sola vez y expone el servicio de la API.
// ================================================================================================
package com.example.apirest.data.remote  // Paquete de acceso remoto


import retrofit2.Retrofit                             // Constructor del cliente HTTP
import retrofit2.converter.gson.GsonConverterFactory  // Conversor JSON <-> objetos


object RetrofitInstance {  // object = instancia única


    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"  // Debe terminar en "/"


    val api: ApiService by lazy {                                // Se crea en el primer uso
        Retrofit.Builder()                                       // Inicia la configuración
            .baseUrl(BASE_URL)                                   // URL base de la API
            .addConverterFactory(GsonConverterFactory.create())  // Convierte JSON con Gson
            .build()                                             // Construye el cliente
            .create(ApiService::class.java)                      // Implementa ApiService
    }
}
