package com.example.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogTheme {
                val context = LocalContext.current
                val scrollState = rememberScrollState()
                var showDialog by remember {
                    mutableStateOf(false)
                }

                JoinScreen(
                    showDialog = showDialog,
                    onToggleDialog = { showDialog = !showDialog },
                    toastMessage = { message -> showToast(context, message) },
                    confirmMessage = "permission accepted",
                    dismissMessage = "no permission accepted",
                    dialogTitle = "Alert dialog example",
                    dialogText = {
                        Box(
                            modifier = Modifier
                                .height(200.dp) // 원하는 높이 지정
                                .verticalScroll(scrollState)
                                .padding(end = 12.dp)

                        ) {
                            Text(
                                text = "This is an example of an alert dialog with buttons.\n".repeat(
                                    50
                                ) // 긴 텍스트 예시
                            )
                        }

                    },
                    icon = Icons.Default.Info
                )

            }
        }
    }
}

fun showToast(context: Context, toastMessage: String) {
    Toast.makeText(
        context,
        toastMessage,
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun JoinScreen(
    showDialog: Boolean,
    onToggleDialog: () -> Unit,
    toastMessage: (String) -> Unit,
    confirmMessage: String,
    dismissMessage: String,
    dialogTitle: String,
    dialogText: @Composable () -> Unit,
    icon: ImageVector,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            onToggleDialog()

        }) {
            Text(text = "Do you want join the chat")
        }
        Spacer(modifier = Modifier.height(32.dp))
        if (showDialog) {
            AlertDialog(
                icon = { Icon(icon, contentDescription = null) },
                title = { Text(text = dialogTitle) },
                text = { dialogText() },
                onDismissRequest = onToggleDialog,
                confirmButton = {
                    TextButton(onClick = {
                        onToggleDialog()
                        toastMessage(confirmMessage)
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        onToggleDialog()
                        toastMessage(dismissMessage)
                    }) {
                        Text("Dismiss")
                    }
                },

                )

        }

    }

}




