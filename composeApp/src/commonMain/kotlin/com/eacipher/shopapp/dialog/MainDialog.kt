package com.eacipher.shopapp.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eacipher.shopapp.dialog.DialogController
import com.eacipher.shopapp.dialog.DialogEvent


@Composable
fun MainDialog(
    dialogController: DialogController
) {

    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            title = null,
            shape = RoundedCornerShape(10.dp),
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text( //Заголовок
                        text = dialogController.dialogTitle.value,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (dialogController.showEditableText.value)
                        TextField(
                            value = dialogController.editableText.value,
                            onValueChange = { text ->
                                dialogController.onDialogEvent(DialogEvent.OnTextChanged(text))
                            },
                            label = {
                                Text(text = "List name")
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogController.onDialogEvent(DialogEvent.OnConfirm)
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogController.onDialogEvent(DialogEvent.OnCancel)
                }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}