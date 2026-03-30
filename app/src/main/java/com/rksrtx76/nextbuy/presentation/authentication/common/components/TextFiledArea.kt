package com.rksrtx76.nextbuy.presentation.authentication.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFiledArea(
    text : String,
    placeHolder : String = "Username or Email",
    onValueChange : (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = placeHolder,
                color = Color.DarkGray.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                tint = Color.DarkGray.copy(alpha = 0.8f),
                modifier = Modifier.size(24.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
            focusedBorderColor = Color.Black.copy(alpha = 0.2f),
            unfocusedBorderColor = Color.Black.copy(alpha = 0.2f),
        ),
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    )
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TextFiledAreaPrev() {
    TextFiledArea("","Username",{},)
}