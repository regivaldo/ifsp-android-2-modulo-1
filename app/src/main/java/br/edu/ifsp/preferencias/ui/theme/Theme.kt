package br.edu.ifsp.preferencias.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = VerdePrimario,
    secondary = RoxoSecundario,
    onPrimary = Color.White,
    onSecondary = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = VerdePrimarioEscuro,
    secondary = RoxoSecundarioEscuro,
    onPrimary = Color.White,
    onSecondary = Color.White
)

@Composable
fun PreferenciasUsuarioTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
