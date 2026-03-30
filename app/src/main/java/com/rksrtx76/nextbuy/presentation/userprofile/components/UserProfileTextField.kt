package com.rksrtx76.nextbuy.presentation.userprofile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserProfileTextField(
    label : String,
    value : String,
    modifier : Modifier = Modifier,
    onValueChange : (String) -> Unit,
    isPassword : Boolean = false,
    trailingIcon : ImageVector? = null,
    onTrailingIconClicked : () -> Unit = {},
    enabled : Boolean = true
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            visualTransformation = if(isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if(trailingIcon != null){
                    IconButton(
                        onClick = onTrailingIconClicked
                    ) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "Trailing Icon",
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color(0xFFE0E0E0),
                unfocusedIndicatorColor = Color(0xFFE0E0E0),
                disabledIndicatorColor = Color(0xFFE0E0E0),
                disabledTextColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp)

        )
    }
}