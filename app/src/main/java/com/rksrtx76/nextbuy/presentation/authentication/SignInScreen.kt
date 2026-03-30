package com.rksrtx76.nextbuy.presentation.authentication

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.presentation.authentication.common.components.BoldTextArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.CustomButton
import com.rksrtx76.nextbuy.presentation.authentication.common.components.PasswordFiledArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.SignInArea
import com.rksrtx76.nextbuy.presentation.authentication.common.components.TextFiledArea
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import com.rksrtx76.nextbuy.util.Result

@Composable
fun SignInScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (val currState = authState){
            is Result.Success -> {
                navController.navigate(Routes.HomeScreen){
                    popUpTo(Routes.SignInScreen){
                        inclusive = true
                    }
                }
            }
            is Result.Error -> {
                showError = true
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
        BoldTextArea(text = "Welcome\nBack!")

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

            if(showError){
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = { navController.navigate(Routes.ForgotPasswordScreen)},
                    contentPadding = PaddingValues(0.dp)   // removed TextButton default padding
                ) {
                    Text(
                        text = "Forgot Password?",
                        fontSize = 14.sp,
                        color = Color(0xFFF83758),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = "Login",
            isLoading = authState is Result.Loading,
            enabled = email.isNotBlank() && password.isNotBlank(),
            onClick = {
                // do authentication
                authViewModel.signIn(email,password)
            }
        )

        Spacer(modifier = Modifier.height(64.dp))

        SignInArea(
            text = "Don't have an account?",
            signInText = "Sign Up",
            onClick = {
                navController.navigate(Routes.SignUpScreen)
            },
            authViewModel = authViewModel
        )

    }
}






//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun SignInScreenPrev() {
//    SignInScreen()
//}