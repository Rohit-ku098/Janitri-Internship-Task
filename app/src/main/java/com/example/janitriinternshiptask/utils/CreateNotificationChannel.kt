package com.example.janitriinternshiptask.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "add_vital_channel",
            "Add Vital",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notification to add vital details."
        }

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}
