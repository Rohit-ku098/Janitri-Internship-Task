package com.example.janitriinternshiptask.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.janitriinternshiptask.ui.theme.primaryColor
import com.example.janitriinternshiptask.ui.theme.purpleText
import com.example.janitriinternshiptask.viewmodel.MainViewModel

@Preview(showBackground = true)
@Composable
fun AddVitalDialog(
    onDismiss: () -> Unit = {},
    onSubmit: (String, String, String, String) -> Unit = { _, _, _, _ -> },
) {
    var sysBp by rememberSaveable { mutableStateOf("") }
    var diaBp by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }
    var babyKicks by rememberSaveable { mutableStateOf("") }
    var sysBpError by rememberSaveable { mutableStateOf(false) }
    var diaBpError by rememberSaveable { mutableStateOf(false) }
    var weightError by rememberSaveable { mutableStateOf(false) }
    var babyKicksError by rememberSaveable { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Add Vitals",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if(isSystemInDarkTheme()) Color.White else purpleText
                )

                Row {
                    OutlinedTextField(
                        value = sysBp,

                        onValueChange = { sysBp = it; sysBpError = false },
                        label = { Text("Sys BP") },
                        isError = sysBpError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.weight(0.2f))
                    OutlinedTextField(
                        value = diaBp,
                        onValueChange = { diaBp = it; diaBpError = false },
                        isError = diaBpError,
                        label = { Text("Dia BP") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f)
                    )
                }


                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it; weightError = false },
                    isError = weightError,
                    label = { Text("Weight (Kg)") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it; babyKicksError = false },
                    isError = babyKicksError,
                    label = { Text("Baby Kicks") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if(sysBp.isEmpty()) {
                                sysBpError = true
                                return@Button
                            }
                            if(diaBp.isEmpty()) {
                                diaBpError = true
                                return@Button
                            }
                            if(weight.isEmpty()) {
                                weightError = true
                                return@Button
                            }
                            if(babyKicks.isEmpty()) {
                                babyKicksError = true
                                return@Button
                            }
                        onSubmit(sysBp, diaBp, weight, babyKicks)
                        onDismiss()
                    },
                        colors = ButtonColors(
                            contentColor = Color.White,
                            containerColor = primaryColor,
                            disabledContainerColor = primaryColor,
                            disabledContentColor = primaryColor,
                        )
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}
