package com.rksrtx76.nextbuy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.rksrtx76.nextbuy.presentation.navigation.AppNavigation
import com.rksrtx76.nextbuy.presentation.paymentSuccessfull.PaymentViewModel
import com.rksrtx76.nextbuy.ui.theme.NextBuyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultListener {

    private val paymentViewModel : PaymentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // below code pre download payment ui so no delay will happen when we click pay
        Checkout.preload(applicationContext)

//        enableEdgeToEdge()

        setContent {
            NextBuyTheme {
                AppNavigation(paymentViewModel = paymentViewModel)
            }
        }
    }

    // Razorpay success callback
    override fun onPaymentSuccess(paymentId: String?) {
        paymentViewModel.onPaymentSuccess(paymentId ?: "NA")
        Log.d("Razorpay", "Payment Success: $paymentId")
    }

    override fun onPaymentError(errorCode: Int, response: String?) {
        val message = when(errorCode){
            0    -> "Network error. Please check your connection."
            1    -> "Payment was cancelled."
            2    -> "Payment failed. Please try a different payment method."
            else -> response ?: "An unexpected error occurred."

        }
        paymentViewModel.onPaymentError(message)
        Log.d("Razorpay", "Payment Error $errorCode : $response")
    }
}
