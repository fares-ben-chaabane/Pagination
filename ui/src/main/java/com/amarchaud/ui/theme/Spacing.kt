package com.amarchaud.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val little : Dp = 10.dp,
    val normal: Dp = 20.dp
)

internal val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current