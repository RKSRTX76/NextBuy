package com.rksrtx76.nextbuy.presentation.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rksrtx76.nextbuy.presentation.authentication.common.components.BoldTextArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.CustomButton
import com.rksrtx76.nextbuy.presentation.authentication.common.components.PasswordFiledArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.SignInArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.TextFiledArea
import com.rksrtx76.nextbuy.presentation.navigation.Routes


@Composable
fun ForgotPasswordScreen(
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BoldTextArea(text = "Forgot\nPassword!")

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            TextFiledArea(
                text = email,
                onValueChange = {
                    email = it
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if(showMessage){
                Text(
                    text = "Reset email sent",
                    fontSize = 12.sp,
                    color = Color(0xFF1AA36D),
                )
            }else{
                Text(
                    text = "* We will send you an email to reset your password ",
                    fontSize = 12.sp,
                    color = Color.Black.copy(alpha = 0.75f),
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = "Submit",
            enabled = email.isNotBlank(),
            isLoading = false,
            onClick = {
                showMessage = true
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomButton(
            text = "Go Back",
            enabled = true,
            isLoading = false,
            onClick = {
                navController.navigate(Routes.SignInScreen)
            }
        )


    }
}