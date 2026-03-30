package com.rksrtx76.nextbuy.presentation.homescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating : Double,
    starSize : Dp = 16.dp,
    maxStars : Int = 5
) {
    Row{
        repeat(maxStars){ index->
            val starRating = when{
                rating >= index + 1 -> 1.0 // Full star
                rating > index -> rating - index // Half star
                else -> 0.0 // Empty star
            }

            Box{
                Icon(
                    imageVector = Icons.Default.StarRate,
                    contentDescription = null,
                    tint = Color(0xFFE0E0E0), // Light gray for empty
                    modifier = Modifier.size(starSize)
                )
                if(starRating > 0){
                    Icon(
                        imageVector = Icons.Default.StarRate,
                        contentDescription = null,
                        tint = Color(0xFFFFA726), // Orange for filled
                        modifier = Modifier
                            .size(starSize)
                            .clipToBounds()
                            .drawWithContent{
                                clipRect(
                                    right = size.width * starRating.toFloat()
                                ){
                                    this@drawWithContent.drawContent()
                                }
                            }
                    )
                }
            }
        }
    }
}