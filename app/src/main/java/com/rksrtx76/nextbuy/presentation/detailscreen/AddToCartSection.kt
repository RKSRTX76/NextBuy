package com.rksrtx76.nextbuy.presentation.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.nextbuy.domain.model.Product
import kotlin.compareTo

@Composable
fun AddToCartSection(
    product: Product,
    onAddToCart: () -> Unit,
    onGoToCart : () -> Unit
){
    var isAddedToCart by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if(isAddedToCart){
            Button(
                onClick = onGoToCart,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                enabled = product.stock > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4285F4),
                    disabledContainerColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (product.stock > 0) "Go to cart" else "Out of Stock",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }else{
            Button(
                onClick = {
                    isAddedToCart = true
                    onAddToCart()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                enabled = product.stock > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4285F4),
                    disabledContainerColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (product.stock > 0) "Add to Cart" else "Out of Stock",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        Button(
            onClick = { },
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            enabled = product.stock > 0,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6B35),
                disabledContainerColor = Color.Gray
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if(product.stock > 0) "Buy Now" else "Out of Stock",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}