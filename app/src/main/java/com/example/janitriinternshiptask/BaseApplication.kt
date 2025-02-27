package com.example.janitriinternshiptask

import android.app.Application
import com.example.janitriinternshiptask.di.appModule
import com.example.janitriinternshiptask.utils.createNotificationChannel
import com.example.janitriinternshiptask.worker.enqueueWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel(this)
        enqueueWorker(this)
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}