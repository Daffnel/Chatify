package com.example.chattlyapp.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun OnlyForPayingCustomers(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("OK")
                }
            },
            title = { Text("Endast för permium användare") },
            text = { Text("Funktionen tillgänlig endast för betalande!!") }
        )
    }
}
