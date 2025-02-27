package com.example.janitriinternshiptask.worker

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun enqueueWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<AddVitalRemindWorker>(5, TimeUnit.HOURS)
        .setInitialDelay(5, TimeUnit.HOURS)
        .setConstraints(
            Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build()
        )
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "add_vital_work",
        ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )

    // Uncomment only for testing purposes
//    runWorkNow(context)
}

fun runWorkNow(context: Context) {
    val workManager = WorkManager.getInstance(context)
    CoroutineScope(Dispatchers.Default).launch {
        delay(5000)
        workManager.getWorkInfosForUniqueWork("add_vital_work").get().forEach { workInfo ->
            if (workInfo.state == WorkInfo.State.ENQUEUED) {
                WorkManager.getInstance(context).enqueue(OneTimeWorkRequestBuilder<AddVitalRemindWorker>().build())
                Log.d("WorkManagerStatus", "Work manually triggered!")
            }
        }
    }

}