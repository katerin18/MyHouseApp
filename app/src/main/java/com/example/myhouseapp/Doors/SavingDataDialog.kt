package com.example.myhouseapp.Doors

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class SavingDataDialog {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditDialog(text: String, onSave: (String) -> Unit) {
        var inputText by remember { mutableStateOf(text) }
        var openAlertDialog by remember { mutableStateOf(true) }

        if (openAlertDialog) {
            AlertDialog(
                onDismissRequest = { openAlertDialog = false },
                title = { Text("Edit door name") },
                text = {
                    TextField(value = inputText, onValueChange = { inputText = it })
                },
                confirmButton = {
                    Button(onClick = {
                        onSave(inputText)
                        openAlertDialog = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { openAlertDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}