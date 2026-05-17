package com.rksrtx76.nextbuy.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.core.app.NotificationCompat
import com.rksrtx76.nextbuy.MainActivity
import com.rksrtx76.nextbuy.R

object OrderNotificationHelper {
    private const val CHANNEL_ID = "order_confirmation_channel"
    private const val CHANNEL_NAME = "order conformation"
    private const val NOTIFICATION_ID = 1001

    fun showOrderConfirmation(
        context : Context,
        paymentId : String,
        amount : String
    ){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create channel (required on API 26+)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notification for order placement confirmation"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        // When tap on notification opens the app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
                context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("\uD83C\uDF89 Order Confirmed!")
            .setContentText("Payment of $amount received.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "Your order has been placed successfully!\n" +
                        "Amount Paid : $amount\n" + "Transaction ID : #$paymentId"
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)

    }
}