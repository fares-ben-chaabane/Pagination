package com.amarchaud.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    onPrimary = Color.White,
    onSecondary = Color.White,
    error = Color.Red,
    onError = Color(0xFFF12000),
)

@Composable
fun PaginationDemoTheme(content: @Composable () -> Unit) {

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}