package com.amarchaud.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amarchaud.ui.screen.mainList.heightOneCell
import com.amarchaud.ui.theme.PaginationDemoTheme

/**
 * Create a basic shimmer
 * User needs to use modifier to fix height/width etc
 */
@Composable
fun ShimmerAnimationItem(
    modifier: Modifier = Modifier
) {
    val shimmerColorShades = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(
        modifier = modifier,
        brush = brush
    )
}

@Composable
private fun ShimmerItem(
    modifier: Modifier = Modifier,
    brush: Brush
) {
    Spacer(
        modifier = modifier
            .background(brush = brush)
    )
}

@Preview
@Composable
fun PreviewShimmerExample() {
    PaginationDemoTheme {
        ShimmerAnimationItem(
            modifier = Modifier
                .size(heightOneCell)
                .clip(shape = CircleShape)
                .border(width = 2.dp, color = Color.Blue, shape = CircleShape)
        )
    }
}