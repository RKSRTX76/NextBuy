package com.rksrtx76.nextbuy.util

import android.app.Activity
import com.razorpay.Checkout
import com.rksrtx76.nextbuy.BuildConfig
import org.json.JSONObject

object RazorpayHelper {
    private const val RAZORPAY_KEY = BuildConfig.RAZORPAY_KEY

    fun startPayment(
        activity: Activity,
        amount : Double,
    ){
        val checkOut = Checkout()
        checkOut.setKeyID(RAZORPAY_KEY)

        val options = JSONObject().apply {
            put("name", "NextBuy")
            put("description", "Test Order")
            put("currency", "INR")
            put("amount", (amount * 100).toInt()) // accepts in paisa
//            put("theme", JSONObject().apply {
//                put("color", "#F83758")
//            })

        }
        checkOut.open(activity, options)
    }
}