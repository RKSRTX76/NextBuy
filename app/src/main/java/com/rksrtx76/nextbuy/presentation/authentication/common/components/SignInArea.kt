package com.rksrtx76.nextbuy.presentation.authentication.common.components

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException

import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.presentation.authentication.AuthViewModel
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import kotlinx.coroutines.launch
import kotlin.math.sign

@Composable
fun SignInArea(
    text : String,
    signInText : String,
    onClick : () -> Unit,
    authViewModel: AuthViewModel
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val activity = context as? Activity ?: return
    val interactionSource = remember { MutableInteractionSource() }
    val credentialManager = remember{
        CredentialManager.create(activity)
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "- OR Continue with - "
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageContainer(
                    imageId = R.drawable.google,
                    onGoogleSignIn = {
                        scope.launch {
                            try {
                                // Google Sign-In Launcher (GetGoogleIdOption used for mini credential manager)
                                // Pass 1: Try fast one-tap for existing/authorized accounts
                                val googleIdOption = GetGoogleIdOption.Builder()
                                    .setFilterByAuthorizedAccounts(true)
                                    .setServerClientId(context.getString(R.string.default_web_client_id))
                                    .build()

                                val googleSignInRequest = GetCredentialRequest.Builder()
                                    .addCredentialOption(googleIdOption)
                                    .build()

                                val result = credentialManager.getCredential(
                                    request = googleSignInRequest,
                                    context = activity
                                )
                                val credential = result.credential
                                val googleIdTokenCredential = GoogleIdTokenCredential
                                    .createFrom(credential.data)
                                val idToken = googleIdTokenCredential.idToken
                                authViewModel.signInWithGoogle(idToken)


                            }catch (e : NoCredentialException){
                                // Pass 2: No authorized account found, show full sheet
                                try {
                                    // Google Sign-In Launcher (GetSignInWithGoogleOption used for full credential manager)
                                    val googleIdOption = GetSignInWithGoogleOption.Builder(
                                        context.getString(R.string.default_web_client_id)
                                    ).build()
                                    val googleSignInRequest = GetCredentialRequest.Builder()
                                        .addCredentialOption(googleIdOption)
                                        .build()

                                    val result = credentialManager.getCredential(
                                        request = googleSignInRequest,
                                        context = activity
                                    )
                                    val credential = result.credential
                                    val googleIdTokenCredential = GoogleIdTokenCredential
                                        .createFrom(credential.data)
                                    val idToken = googleIdTokenCredential.idToken
                                    authViewModel.signInWithGoogle(idToken)

                                } catch (e: GetCredentialCancellationException) {
                                    // User dismissed, do nothing
                                } catch (e: GetCredentialException) {
                                    Log.e("GoogleSignIn", "Fallback Error: ${e.message}")
                                }

                            } catch (e : GetCredentialCancellationException){
                                // User dismissed , so do nothing
                            }
                            catch (e : GetCredentialException){
                                Log.e("GoogleSignIn", "Credential Error : ${e.message}")
                            }
                        }
                    }
                )
//                ImageContainer(imageId = R.drawable.apple)
//                ImageContainer(imageId = R.drawable.facebook)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = text)
                Text(
                    text = signInText,
                    fontSize = 14.sp,
                    color = Color(0xFFF83758),
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ){
                            onClick()
                        }
                )
            }
        }
    }
}


@Composable
fun ImageContainer(
    imageId : Int,
    onGoogleSignIn : () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .height(48.dp)
            .border(1.dp,Color.Black.copy(alpha = 0.25f),RoundedCornerShape(6.dp))
            .clickable{
                onGoogleSignIn()
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = "Sign in",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(42.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Continue with Google",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}
