package com.rksrtx76.nextbuy.presentation.paymentSuccessfull

data class PaymentState(
    val isLoading : Boolean? = false,
    val isSuccess : Boolean? = null,
    val paymentId : String? = null,
    val errorMessage : String? = null,
    val amount : Double = 0.0
)