package com.duhdoesk.supertrunfoclone.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = lightColors(
    primary = Yellow,
    primaryVariant = Yellow,
    secondary = LighterGrey,
    secondaryVariant = LighterGrey,
    background = DarkGrey,
    surface = DarkGrey,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun SuperTrunfoTheme(
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}