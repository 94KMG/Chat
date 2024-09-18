package com.example.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogTheme {

                Row(
                    Modifier.fillMaxSize()
                ) {
                    val context = LocalContext.current
                    AlertDialogExample(
                        onDismiss = {
                            Toast.makeText(
                                context,
                                "no permission accepted",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onConfirmation = {
                            Toast.makeText(
                                context,
                                "permission accepted",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        dialogTitle = "Alert dialog example",
                        dialogText = "This is an example of an alert dialog with buttons.",
                        icon = Icons.Default.Info
                    )
                }
            }
        }
    }
}


@Composable
fun AlertDialogExample(
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = null) },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}


@Preview
@Composable
fun AlertDialogExamplePreview() {
    val context = LocalContext.current


    Row(
        Modifier.fillMaxSize()
    ) {

    }
    AlertDialogExample(
        onDismiss = {
            Toast.makeText(context, "no permission accepted", Toast.LENGTH_SHORT).show()
        },
        onConfirmation = {
            Toast.makeText(context, "permission accepted", Toast.LENGTH_SHORT).show()
        },
        dialogTitle = "Alert dialog example",
        dialogText = "This is an example of an alert dialog with buttons.",
        icon = Icons.Default.Info
    )
}



