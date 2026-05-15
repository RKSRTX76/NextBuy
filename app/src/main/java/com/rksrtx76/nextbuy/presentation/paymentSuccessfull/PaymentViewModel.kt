package com.rksrtx76.nextbuy.presentation.paymentSuccessfull

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor() : ViewModel() {
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