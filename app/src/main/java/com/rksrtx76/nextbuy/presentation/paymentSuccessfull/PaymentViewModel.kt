package com.rksrtx76.nextbuy.presentation.paymentSuccessfull

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.rksrtx76.nextbuy.util.OrderNotificationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    @ApplicationContext private val context : Context
) : ViewModel() {
    private val _state = MutableStateFlow(PaymentState())
    val state = _state.asStateFlow()

    fun onPaymentStarted(amount : Double){
        _state.value = _state.value.copy(
            isLoading = true,
            amount = amount
        )
    }

    fun onPaymentSuccess(paymentId : String){
        _state.value = _state.value.copy(
            isLoading = false,
            isSuccess = true,
            paymentId = paymentId
        )
        // Push notification
        OrderNotificationHelper.showOrderConfirmation(
            context = context,
            paymentId = paymentId,
            amount = "₹${"%.2f".format(_state.value.amount)}"
        )
    }

    fun onPaymentError(errorMessage : String){
        _state.value = _state.value.copy(
            isLoading = false,
            isSuccess = false,
            errorMessage = errorMessage
        )
    }

    fun resetPayment(){
        _state.value = PaymentState()
    }


}