
package com.github.lara.sodre.mobilityplus_gs.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MobilityColorScheme = lightColorScheme(

    primary = AzulPrincipal,
    onPrimary = Branco,

    secondary = AzulEscuro,
    onSecondary = Branco,

    background = Fundo,
    onBackground = TextoPrincipal,

    surface = Branco,
    onSurface = TextoPrincipal,

    error = VermelhoErro
)

@Composable
fun MobilityPlusTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = MobilityColorScheme,
        typography = Typography,
        content = content
    )
}
