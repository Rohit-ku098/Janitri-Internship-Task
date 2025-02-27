package com.example.janitriinternshiptask

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.janitriinternshiptask.component.AddVitalDialog
import com.example.janitriinternshiptask.component.FAB
import com.example.janitriinternshiptask.component.VitalLogComponent
import com.example.janitriinternshiptask.model.VitalLog
import com.example.janitriinternshiptask.ui.theme.JanitriInternshipTaskTheme
import com.example.janitriinternshiptask.ui.theme.purpleBackground
import com.example.janitriinternshiptask.ui.theme.purpleText
import com.example.janitriinternshiptask.utils.requestNotificationPermission
import com.example.janitriinternshiptask.utils.showNotification
import com.example.janitriinternshiptask.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestNotificationPermission(this)


        setContent {
            JanitriInternshipTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(modifier = Modifier.padding(innerPadding)) {
                        mainScreen(viewModel, this@MainActivity)
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, heightDp = 700, widthDp = 400)
@Composable
fun mainScreen(viewModel: MainViewModel, context: Context) {
    val vitalList: List<VitalLog> by viewModel.vitalList.collectAsState(emptyList())
    var showDialog by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val intent = (context as Activity).intent
        val navigateTo = intent.getStringExtra("navigateTo")
        if (navigateTo == "add_vital") {
            showDialog = true
        }
    }

    Box() {
        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = purpleBackground),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Track My Pregnancy",
                    fontWeight = FontWeight.Bold,
                    color = purpleText,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(8.dp),

                )
            }

            LazyColumn {
                itemsIndexed(vitalList) { index, vitalLog ->
                   if(index == 0) {
                        VitalLogComponent(modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),vitalLog = vitalLog)
                   }
                   else {
                       VitalLogComponent(vitalLog = vitalLog)
                   }
                }
            }
        }
        FAB({
            showDialog = !showDialog
        })
        if (showDialog) {
            AddVitalDialog(
                onDismiss = { showDialog = false },
                onSubmit = { sys, dia, weight, kicks ->
                    val vitalLog = VitalLog(systolic = sys+"bpm", diastolic = dia+"mmHg", weight = weight+"kg", kickCount = kicks+"kicks", timeStamp = System.currentTimeMillis())
                    viewModel.addVitalLog(vitalLog)
                    Log.d("Vitals", "Sys BP: $sys, Dia BP: $dia, Weight: $weight, Baby Kicks: $kicks")
                }
            )
        }
    }
}
