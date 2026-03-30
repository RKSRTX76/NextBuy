package com.rksrtx76.nextbuy.presentation.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.presentation.homescreen.components.StarRating
import kotlin.math.roundToInt

@Composable
fun ProductDetails(
    product: Product
){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if(product.brand.isNotEmpty()){
            Text(
                text = product.brand,
                fontSize = 14.sp,
                color = Color(0xFF4285F4),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Text(
            text = product.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Rating
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StarRating(
                rating = product.rating,
                starSize = 18.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = String.format("%.1f", product.rating),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            // Discount Percentage
            if(product.discountPercentage > 0){
                Text(
                    text = "₹${product.price.roundToInt()}",
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.LineThrough,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            // Discount Price
            Text(
                text = "₹${product.discountedPrice.roundToInt()}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            if(product.discountPercentage > 0){
                Text(
                    text = "${product.discountPercentage.roundToInt()}% Off",
                    fontSize = 12.sp,
                    color = Color(0xFFFF6200)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category
        if(product.category.isNotEmpty()){
            Text(
                text = "Category : ${product.category.replaceFirstChar { it.uppercase() }}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Stock status
        Text(
            text = if(product.stock > 0) "${product.stock} available" else "Out of Stock",
            fontSize = 14.sp,
            color = if(product.stock > 0) Color(0xFF4CAF50) else Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = product.description,
            fontSize = 16.sp,
            color = Color.Gray,
            lineHeight = 24.sp
        )

    }
}