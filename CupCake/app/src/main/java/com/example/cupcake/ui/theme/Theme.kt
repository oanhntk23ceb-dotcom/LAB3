// File: com/example/cupcake/ui/theme/Theme.kt

package com.example.cupcake.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// You must define these colors in Color.kt
private val LightColorScheme = lightColorScheme(
    primary = Pink40,
    secondary = PurpleGrey40,
    tertiary = Pink80,
)

@Composable
fun CupcakeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography, // Defined in Type.kt
        content = content
    )
}