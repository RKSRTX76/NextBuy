package com.rksrtx76.nextbuy.presentation.onboarding.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    currentPage: Int,
    onPrev : () -> Unit = { },
    onNext : () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ){
            if(currentPage != 1){
                TextButton(
                    onClick =  onPrev
                ) {
                    Text(
                        text = "Prev",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(alpha = 0.35f)
                    )
                }
            }
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            ButtonIndicator(
                totalPages = 3,
                currentPage = currentPage,
                modifier = Modifier
            )
        }


        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ){
            TextButton(
                onClick = onNext
            ) {
                Text(
                    text = if(currentPage == 3) "Get Started" else "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFF83758)
                )
            }
        }
    }
}