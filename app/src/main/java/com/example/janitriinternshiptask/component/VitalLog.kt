package com.example.janitriinternshiptask.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.janitriinternshiptask.R
import com.example.janitriinternshiptask.model.VitalLog
import com.example.janitriinternshiptask.ui.theme.cardBackgroundColor
import com.example.janitriinternshiptask.ui.theme.primaryColor
import java.text.SimpleDateFormat
import java.util.Locale

//@Preview(name = "vitalLog", showBackground = true)
@Composable
fun VitalLogComponent(modifier: Modifier = Modifier, vitalLog: VitalLog) {
    Box(modifier = modifier
        .padding(16.dp, 8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color = cardBackgroundColor)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                imageText(modifier = Modifier.weight(1f),image = R.drawable.systolic, text = vitalLog.systolic)
                imageText(modifier = Modifier.weight(1f), image = R.drawable.diastolic, text = vitalLog.diastolic)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                imageText(modifier = Modifier.weight(1f), image = R.drawable.weighing, text = vitalLog.weight)
                imageText(modifier = Modifier.weight(1f), image = R.drawable.baby, text = vitalLog.kickCount)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = primaryColor),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.ENGLISH).format(vitalLog.timeStamp),
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}

@Composable
fun imageText(modifier: Modifier = Modifier, image: Int, text: String) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.padding(8.dp).size(30.dp),
                colorFilter = ColorFilter.tint(Color.Black)
            )
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
