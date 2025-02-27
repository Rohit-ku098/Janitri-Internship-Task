package com.example.janitriinternshiptask.model


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [VitalLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vitalLogDao(): VitalLogDao
}
