package com.example.janitriinternshiptask.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.janitriinternshiptask.model.AppDatabase
import com.example.janitriinternshiptask.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideRoomInstance(get()) }
    single { provideVitalLogDao(get()) }

    viewModel { MainViewModel(get()) }
}


fun provideRoomInstance(application: Application) : AppDatabase =
    Room.databaseBuilder(application, AppDatabase::class.java, "JanitriInternshipTask")
        .fallbackToDestructiveMigration()
        .build().also {
            Log.d("JanitriInternshipTask", "Room instance successfully created.")
        }
fun provideVitalLogDao(appDatabase: AppDatabase) = appDatabase.vitalLogDao()