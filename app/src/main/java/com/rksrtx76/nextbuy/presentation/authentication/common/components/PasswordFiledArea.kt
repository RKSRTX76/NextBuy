package com.rksrtx76.nextbuy.presentation.authentication.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.nextbuy.R

@Composable
fun PasswordFiledArea(
    password : String,
    placeHolder : String = "Password",
    onValueChange : (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
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
                imageVector = Icons.Default.Lock,
                contentDescription = "Password",
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
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisible = !passwordVisible
                }
            ) {
                Icon(
                    painter = painterResource(if(passwordVisible) R.drawable.ic_visibilty_on else R.drawable.ic_visibility_off),
                    contentDescription = "Password visibility",
                    tint = Color.DarkGray.copy(alpha = 0.8f),
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        visualTransformation =  if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    )
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TextFiledAreaPrev() {
    PasswordFiledArea("","Password",{},)
}