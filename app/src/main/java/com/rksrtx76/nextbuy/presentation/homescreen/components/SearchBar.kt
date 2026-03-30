package com.rksrtx76.nextbuy.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.presentation.navigation.Routes

@Composable
fun SearchBar(
   searchQuery : String,

   navController : NavHostController
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
//            .padding(horizontal = 16.dp, vertical = 2.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ){
                navController.navigate(Routes.SearchScreen)
            }
            .clip(RoundedCornerShape(8.dp))
    ){
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            placeholder = {
                Text(
                    text = "Search product",
                    color = Color(0xFFBBBBBB),
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_magnifier),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_mic),
                        contentDescription = "Voice Search",
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            enabled = false,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

}