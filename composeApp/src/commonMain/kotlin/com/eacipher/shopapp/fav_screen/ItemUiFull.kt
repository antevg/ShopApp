package com.eacipher.shopapp.fav_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
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
import com.eacipher.shopapp.dialog.DialogEvent

@Composable
fun ItemUiFull(itemFullController: ItemFullController) {
    if (itemFullController.openItemFull.value) {
        Scaffold(

        ) {
            Card(
                modifier = Modifier
                    .padding(top = 120.dp)
                    .fillMaxSize()

            ) {

                Column {

                    Column(modifier = Modifier.fillMaxWidth()) {
                        TextButton( //Описание
                            onClick = {
                                itemFullController.onItemFullEvent(ItemFullEvent.onClose)
                            }) {
                            Text("Close")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text( //Заголовок
                            text = itemFullController.name.value,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text( //Описание
                                text = itemFullController.description.value,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }




                    }
                }
            }
        }
    }
}