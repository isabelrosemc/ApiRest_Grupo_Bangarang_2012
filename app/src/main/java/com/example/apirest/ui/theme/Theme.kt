// ================================================================================================
// Archivo   : ui/theme/Theme.kt
// Propósito : Define ApiRestTheme, el tema Material 3 que envuelve toda la interfaz.
// ================================================================================================
package com.example.apirest.ui.theme  // Paquete del tema

import android.os.Build                                    // Versión de Android del equipo
import androidx.compose.foundation.isSystemInDarkTheme     // Detecta modo oscuro
import androidx.compose.material3.MaterialTheme            // Tema Material 3
import androidx.compose.material3.darkColorScheme          // Esquema de colores oscuro
import androidx.compose.material3.dynamicDarkColorScheme   // Colores dinámicos oscuros
import androidx.compose.material3.dynamicLightColorScheme  // Colores dinámicos claros
import androidx.compose.material3.lightColorScheme         // Esquema de colores claro
import androidx.compose.runtime.Composable                 // Anotación @Composable
import androidx.compose.ui.platform.LocalContext           // Contexto actual de Compose

private val DarkColorScheme = darkColorScheme(  // Paleta para modo oscuro
    primary = Blue80,                           // Color primario
    secondary = BlueGrey80,                     // Color secundario
    tertiary = Teal80                           // Color terciario
)

private val LightColorScheme = lightColorScheme(  // Paleta para modo claro
    primary = Blue40,                             // Color primario
    secondary = BlueGrey40,                       // Color secundario
    tertiary = Teal40                             // Color terciario
)

// ================================================================================================
// Función   : ApiRestTheme
// Propósito : Elige la paleta (clara, oscura o dinámica) y la aplica al contenido recibido.
// Parámetros: darkTheme    -> true si el equipo está en modo oscuro.
//             dynamicColor -> true para usar colores del fondo de pantalla (Android 12+).
//             content      -> interfaz que recibirá el tema.
// ================================================================================================
@Composable                                                                  // Función de interfaz
fun ApiRestTheme(                                                            // Tema de la app
    darkTheme: Boolean = isSystemInDarkTheme(),                              // Modo oscuro actual
    dynamicColor: Boolean = false,                                           // Usa paleta propia
    content: @Composable () -> Unit                                          // Contenido a decorar
) {
    val colorScheme = when {                                                 // Selecciona la paleta
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Android 12 o más
            val context = LocalContext.current                               // Contexto de colores
            if (darkTheme) dynamicDarkColorScheme(context)                   // Dinámica oscura
            else dynamicLightColorScheme(context)                            // Dinámica clara
        }
        darkTheme -> DarkColorScheme                                         // Paleta oscura propia
        else -> LightColorScheme                                             // Paleta clara propia
    }

    MaterialTheme(                  // Aplica el tema Material 3
        colorScheme = colorScheme,  // Colores elegidos
        typography = Typography,    // Tipografía de Type.kt
        content = content           // Interfaz decorada
    )
}