package com.example.janitriinternshiptask.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.janitriinternshiptask.ui.theme.primaryColor


@Composable
fun FAB(onClick: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize().padding(20.dp),
    ) {
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            FloatingActionButton(
                onClick = {onClick() },
                containerColor = primaryColor
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Button",
                    tint = Color.White
                )
            }
        }
    }
}