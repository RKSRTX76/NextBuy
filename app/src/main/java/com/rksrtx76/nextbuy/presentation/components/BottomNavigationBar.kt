package com.rksrtx76.nextbuy.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import okhttp3.Route

@Composable
fun BottomNavigationBar(
    currRoute : Routes,
    onItemClick : (BottomNavItem) -> Unit
){
    val leftItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search
    )
    val rightItems = listOf(
        BottomNavItem.Wishlist,
        BottomNavItem.Profile
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // left items
            leftItems.forEach { item->
                BottomNavItemView(
                    item = item,
                    isSelected = currRoute == item.route,
                    onClick = {
                        onItemClick(item)
                    }
                )
            }

            // Space for cart
            Spacer(modifier = Modifier.width(56.dp))


            // right items
            rightItems.forEach { item->
                BottomNavItemView(
                    item = item,
                    isSelected = currRoute == item.route,
                    onClick = {
                        onItemClick(item)
                    }
                )
            }
        }

        // Floating cart button
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = (-20).dp
                )
                .size(56.dp)
                .shadow(8.dp, CircleShape)
                .background(Color.White, CircleShape)
                .clickable{
                    onItemClick(BottomNavItem.Cart)
                }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_round_background_cart),
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp)
            )
        }

    }
}


@Composable
fun BottomNavItemView(
    item : BottomNavItem,
    isSelected : Boolean,
    onClick : () -> Unit
){
    val iconTint = if (isSelected) Color(0xFFF83758) else Color(0xFF666666)
    val textColor = if (isSelected) Color(0xFFF83758) else Color(0xFF666666)

    val interactionSource = remember { MutableInteractionSource() }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(horizontal = 12.dp)
    ){
        Image(
            painter = painterResource(item.icon),
            contentDescription = item.title,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Text(
            text = item.title,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = if(isSelected) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}