package com.example.janitriinternshiptask.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface VitalLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVitalLog(vitalLog: VitalLog)

    @Query("SELECT * FROM vital_log ORDER BY id DESC")
    fun getAllVitalLogs(): Flow<List<VitalLog>>
}