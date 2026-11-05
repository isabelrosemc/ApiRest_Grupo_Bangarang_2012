// ================================================================================================
// Archivo   : ui/theme/Type.kt
// Propósito : Tipografía (estilos de texto) del tema Material 3.
// ================================================================================================
package com.example.apirest.ui.theme  // Paquete del tema

import androidx.compose.material3.Typography     // Clase de tipografía M3
import androidx.compose.ui.text.TextStyle        // Estilo de un texto
import androidx.compose.ui.text.font.FontFamily  // Familia de fuente
import androidx.compose.ui.text.font.FontWeight  // Grosor de la fuente
import androidx.compose.ui.unit.sp               // Unidad sp para textos

// ================================================================================================
// Valor     : Typography
// Propósito : Redefine bodyLarge; el resto de estilos usa los valores por defecto de Material 3.
// ================================================================================================
val Typography = Typography(              // Tipografía de la app
    bodyLarge = TextStyle(                // Estilo del texto principal
        fontFamily = FontFamily.Default,  // Fuente del sistema
        fontWeight = FontWeight.Normal,   // Grosor normal
        fontSize = 16.sp,                 // Tamaño de letra
        lineHeight = 24.sp,               // Alto de línea
        letterSpacing = 0.5.sp            // Espacio entre letras
    )
)