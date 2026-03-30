package com.rksrtx76.nextbuy.presentation.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterRow(
    showCategoryFilter : Boolean,
    onCategoryClick : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            TextButton(
                onClick = { /* Handle sort */ }
            ) {
                Text("Sort")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.AutoMirrored.Filled.CompareArrows,
                    contentDescription = "Sort",
                    modifier = Modifier
                        .size(16.dp)
                        .rotate(90f)
                )
            }

            TextButton(
                onClick = {
                    onCategoryClick()
                }
            ) {
                Text(
                    text = "Filter",
                    color = if(showCategoryFilter) MaterialTheme.colorScheme.primary else Color.Unspecified
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    Icons.Default.FilterAlt,
                    contentDescription = "Filter",
                    modifier = Modifier.size(16.dp),
                    tint = if(showCategoryFilter) MaterialTheme.colorScheme.primary else Color.Unspecified
                )
            }
        }
    }
}