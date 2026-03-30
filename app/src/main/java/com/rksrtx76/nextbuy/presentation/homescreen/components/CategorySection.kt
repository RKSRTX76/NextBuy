package com.rksrtx76.nextbuy.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.presentation.homescreen.ProductViewModel

data class CustomCategory(
    val name : String,
    val imageRes : Int,
    val slug : String
)


@Composable
fun CategorySection(
    productViewModel: ProductViewModel
){
    val selectedCategory by productViewModel.selectedCategory.collectAsState()

    val categories = listOf(
        CustomCategory("Beauty", R.drawable.round_beauty_image, "beauty"),
        CustomCategory("Fashion", R.drawable.round_fashion_image, "fragrances"),
        CustomCategory("Kids", R.drawable.round_kids_image, "furniture"),
        CustomCategory("Mens", R.drawable.round_mens_image, "mens-shirts"),
        CustomCategory("Womens", R.drawable.round_womens_image, "womens-dresses")
    )


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories){ category->
            CategoryItem(
                category = category,
                isSelected = selectedCategory == category.slug,
                onClick = {
                    if(selectedCategory == category.slug){
                        productViewModel.clearCategoryFilter()
                    }else{
                        productViewModel.filterByCategory(category.slug)
                    }
                }
            )
        }
    }
}


@Composable
fun CategoryItem(
    category: CustomCategory,
    isSelected : Boolean,
    onClick : () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(64.dp)
            .clickable{
                onClick()
            }
    ) {
        Image(
            painter = painterResource(category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .border(
                    width = if(isSelected) 2.dp else 1.dp,
                    color = if(isSelected) MaterialTheme.colorScheme.primary else Color(0xFFE0E0E0),
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}