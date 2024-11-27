package com.amarchaud.ui.screen.mainList.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.amarchaud.ui.composables.ShimmerAnimationItem
import com.amarchaud.ui.screen.mainList.heightOneCell

@Composable
internal fun OneUserShimmer(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShimmerAnimationItem(
            modifier = Modifier
                .size(heightOneCell)
                .clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ShimmerAnimationItem(Modifier.fillMaxWidth().height(16.dp))

        Divider(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 32.dp)
        )
    }
}
