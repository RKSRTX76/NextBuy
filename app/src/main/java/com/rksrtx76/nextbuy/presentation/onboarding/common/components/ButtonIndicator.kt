package com.rksrtx76.nextbuy.presentation.onboarding.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor : Color = Color(0xFF1F2937),
    inactiveColor : Color = Color(0xFFE5E7EB),
    indicatorHeight : Dp = 6.dp,
    indicatorWidth : Dp = 24.dp,
    indicatorSpacing : Dp = 8.dp,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(indicatorSpacing),
        verticalAlignment = Alignment.CenterVertically
        ) {
        repeat(totalPages){ idx->
            Box(
                modifier = Modifier
                    .width(
                        if(idx == currentPage-1) indicatorWidth else indicatorHeight*2
                    )
                    .height(indicatorHeight)
                    .background(
                        color = if (idx == currentPage-1) activeColor else inactiveColor,
                        shape = RoundedCornerShape(indicatorHeight/2)
                    ),
            )
        }
    }
}