package com.example.janitriinternshiptask.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.janitriinternshiptask.model.VitalLog
import com.example.janitriinternshiptask.model.VitalLogDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(val vitalLogDao: VitalLogDao): ViewModel() {
    val vitalList : StateFlow<List<VitalLog>> = vitalLogDao.getAllVitalLogs().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addVitalLog(vitalLog: VitalLog) {
        viewModelScope.launch {
            vitalLogDao.insertVitalLog(vitalLog)
        }
    }
}