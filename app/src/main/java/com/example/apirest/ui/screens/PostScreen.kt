// ================================================================================================
// Archivo   : PostScreen.kt
// Paquete   : com.example.apirest.ui.screens
// Propósito : Pantalla principal. Observa el estado del ViewModel y muestra carga, error o lista.
// ================================================================================================
package com.example.apirest.ui.screens  // Paquete de pantallas


import androidx.compose.foundation.layout.Arrangement        // Separación entre elementos
import androidx.compose.foundation.layout.Box                // Contenedor apilado
import androidx.compose.foundation.layout.Column             // Contenedor vertical
import androidx.compose.foundation.layout.PaddingValues      // Márgenes internos de listas
import androidx.compose.foundation.layout.Spacer             // Espacio vacío
import androidx.compose.foundation.layout.fillMaxSize        // Ocupa todo el espacio
import androidx.compose.foundation.layout.fillMaxWidth       // Ocupa todo el ancho
import androidx.compose.foundation.layout.height             // Alto fijo
import androidx.compose.foundation.layout.padding            // Margen interno
import androidx.compose.foundation.lazy.LazyColumn           // Lista vertical eficiente
import androidx.compose.foundation.lazy.items                // Recorre una lista en LazyColumn
import androidx.compose.material3.Button                     // Botón Material 3
import androidx.compose.material3.Card                       // Tarjeta Material 3
import androidx.compose.material3.CardDefaults               // Valores por defecto de Card
import androidx.compose.material3.CircularProgressIndicator  // Indicador de carga
import androidx.compose.material3.ExperimentalMaterial3Api   // API experimental de M3
import androidx.compose.material3.MaterialTheme              // Acceso al tema
import androidx.compose.material3.Scaffold                   // Estructura base de pantalla
import androidx.compose.material3.Text                       // Texto
import androidx.compose.material3.TopAppBar                  // Barra superior
import androidx.compose.runtime.Composable                   // Anotación @Composable
import androidx.compose.runtime.collectAsState               // Flow -> State de Compose
import androidx.compose.runtime.getValue                     // Permite usar "by"
import androidx.compose.ui.Alignment                         // Alineaciones
import androidx.compose.ui.Modifier                          // Modificadores de UI
import androidx.compose.ui.unit.dp                           // Unidad dp
import com.example.apirest.data.model.Post                   // Modelo de datos
import com.example.apirest.viewmodel.PostViewModel           // ViewModel de la pantalla


// ================================================================================================
// Función   : PostScreen
// Propósito : Arma la pantalla con Scaffold y decide qué mostrar según el estado actual.
// Parámetros: viewModel -> instancia de PostViewModel creada en MainActivity.
// ================================================================================================
@OptIn(ExperimentalMaterial3Api::class)                // TopAppBar es experimental
@Composable                                            // Función de interfaz
fun PostScreen(viewModel: PostViewModel) {             // Pantalla principal
    val uiState by viewModel.uiState.collectAsState()  // Observa el estado del VM
    val error = uiState.errorMessage                   // Copia local del error


    Scaffold(                                                     // Estructura con barra superior
        topBar = {                                                // Contenido de la barra
            TopAppBar(title = { Text("Listado de Posts") })       // Título de la pantalla
        }
    ) { innerPadding ->                                           // Márgenes seguros del sistema
        Box(                                                      // Contenedor del contenido
            modifier = Modifier                                   // Modificadores del Box
                .fillMaxSize()                                    // Ocupa toda la pantalla
                .padding(innerPadding),                           // Respeta barras (edge-to-edge)
            contentAlignment = Alignment.Center                   // Centra carga y error
        ) {
            when {                                                // Elige qué mostrar
                uiState.isLoading -> CircularProgressIndicator()  // 1) Cargando
                error != null -> ErrorContent(                    // 2) Hubo un error
                    message = error,                              // Texto del error
                    onRetry = { viewModel.fetchPosts() }          // Acción del botón
                )
                else -> PostList(posts = uiState.posts)           // 3) Datos listos
            }
        }
    }
}


// ================================================================================================
// Función   : PostList
// Propósito : Muestra los posts en una lista desplazable; solo dibuja los elementos visibles.
// ================================================================================================
@Composable                                               // Función de interfaz
private fun PostList(posts: List<Post>) {                 // Lista de posts
    LazyColumn(                                           // Lista vertical perezosa
        modifier = Modifier.fillMaxSize(),                // Ocupa todo el espacio
        contentPadding = PaddingValues(16.dp),            // Margen alrededor de la lista
        verticalArrangement = Arrangement.spacedBy(8.dp)  // Separación entre tarjetas
    ) {
        items(posts, key = { it.id }) { post ->           // Una fila por cada post
            PostCard(post = post)                         // Dibuja la tarjeta
        }
    }
}


// ================================================================================================
// Función   : PostCard
// Propósito : Tarjeta que muestra el número, el título y el cuerpo de un post.
// ================================================================================================
@Composable                                                              // Función de interfaz
private fun PostCard(post: Post) {                                       // Tarjeta de un post
    Card(                                                                // Tarjeta Material 3
        modifier = Modifier.fillMaxWidth(),                              // Ancho completo
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)  // Sombra
    ) {
        Column(modifier = Modifier.padding(16.dp)) {                     // Contenido en columna
            Text(                                                        // Título del post
                text = "#${post.id} - ${post.title}",                    // Número y título
                style = MaterialTheme.typography.titleMedium             // Estilo de título
            )
            Spacer(modifier = Modifier.height(4.dp))                     // Espacio de 4 dp
            Text(                                                        // Cuerpo del post
                text = post.body,                                        // Contenido del post
                style = MaterialTheme.typography.bodyMedium              // Estilo de cuerpo
            )
        }
    }
}


// ================================================================================================
// Función   : ErrorContent
// Propósito : Muestra el mensaje de error y un botón para volver a intentar la descarga.
// ================================================================================================
@Composable                                                       // Función de interfaz
private fun ErrorContent(message: String, onRetry: () -> Unit) {  // Vista de error
    Column(                                                       // Contenedor vertical
        modifier = Modifier.padding(24.dp),                       // Margen interno
        horizontalAlignment = Alignment.CenterHorizontally        // Centra horizontalmente
    ) {
        Text(                                                     // Mensaje de error
            text = message,                                       // Texto recibido
            color = MaterialTheme.colorScheme.error               // Color de error del tema
        )
        Spacer(modifier = Modifier.height(12.dp))                 // Espacio de 12 dp
        Button(onClick = onRetry) {                               // Botón reintentar
            Text("Reintentar")                                    // Texto del botón
        }
    }
}
