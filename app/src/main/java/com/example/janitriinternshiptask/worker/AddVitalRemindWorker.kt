package com.example.janitriinternshiptask.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.janitriinternshiptask.utils.showNotification

class AddVitalRemindWorker(
    context: Context,
    workerParameters: WorkerParameters
): Worker(context,workerParameters ) {
    override fun doWork(): Result {
        return try {
           showNotification(
               applicationContext,
               title = "Time to log your vitals!",
               message = "Stay on top of your health. Please update your vitals now!"
           )
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}