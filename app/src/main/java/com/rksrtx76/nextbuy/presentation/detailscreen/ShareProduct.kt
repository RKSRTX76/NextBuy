package com.rksrtx76.nextbuy.presentation.detailscreen

import android.content.Context
import android.content.Intent
import com.rksrtx76.nextbuy.domain.model.Product

fun shareProduct(
    product : Product,
    context : Context
){
    val shareText = "Checkout this product : ${product.title}\n" +
                    "${product.description}\n" +
                    "Get it now on NextBuy"

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
        putExtra(Intent.EXTRA_SUBJECT, "Check out this product : ${product.title}")
    }

    val chooserIntent = Intent.createChooser(shareIntent, "Share Product")
    context.startActivity(chooserIntent)
}