package com.example.janitriinternshiptask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vital_log")
data class VitalLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systolic: String,
    val diastolic: String,
    val weight: String,
    val kickCount: String,
    val timeStamp: Long
)
