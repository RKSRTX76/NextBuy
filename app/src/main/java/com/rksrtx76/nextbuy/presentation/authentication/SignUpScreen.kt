package com.rksrtx76.nextbuy.presentation.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rksrtx76.nextbuy.util.Result
import com.rksrtx76.nextbuy.presentation.authentication.common.components.BoldTextArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.CustomButton
import com.rksrtx76.nextbuy.presentation.authentication.common.components.PasswordFiledArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.SignInArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.TextFiledArea
import com.rksrtx76.nextbuy.presentation.navigation.Routes

@Composable
fun SignUpScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (val currState = authState){
            is Result.Success -> {
                navController.navigate(Routes.HomeScreen){
                    popUpTo(Routes.SignUpScreen){
                        inclusive = true
                    }
                }
                // reset authState (if we do not reset what will happen is when navigate login screen ,
                // logic screen launchEffect runs, as Resource is Success it redirects to Homescreen)
                // to avoid that we reset state before going to Sign-in screen
//                authViewModel.resetAuthState()
            }
            is Result.Error -> {
                errorMessage = currState.message
            }
            is Result.Loading -> {
                // Loading Indicator handled inside button
            }
            null ->{
                // I created null branch because in initial state I do not have any state, so I mark as null
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BoldTextArea(text = "Create an\nAccount")

        Spacer(modifier = Modifier.height(20.dp))

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
            Spacer(modifier = Modifier.height(16.dp))

            PasswordFiledArea(
                password = password,
                onValueChange = {
                    password = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))


            PasswordFiledArea(
                password = confirmPassword,
                placeHolder = "Confirm Password",
                onValueChange = {
                    confirmPassword = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "By clicking the button, you are agreeing to our terms and conditions",
                    fontSize = 12.sp,
                    color = Color.Black.copy(alpha = 0.75f),
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if(showError){
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        CustomButton(
            text = "Create Account",
            isLoading = authState is Result.Loading,
            enabled = email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank(),
            onClick = {
                if(password != confirmPassword){
                    errorMessage = "Passwords do not match"
                    showError = true
                }else{
                    authViewModel.signUp(email, password)
                }
            }
        )

        Spacer(modifier = Modifier.height(60.dp))

        SignInArea(
            text = "I already have an account",
            signInText = "Login",
            onClick = {
                navController.navigate(Routes.SignInScreen)
            },
            authViewModel = authViewModel
        )
    }
}


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun SignUpScreenPrev() {
//    SignUpScreen(
//        navController = rememberNavController()
//    )
//
//}