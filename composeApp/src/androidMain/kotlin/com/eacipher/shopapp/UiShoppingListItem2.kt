package com.eacipher.ShoppingKMP.shopping_list_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun UiShoppingListItem2() // (  item: Shopping_list_name,
//  onEvent: (ShoppingListEvent) -> Unit) {
{
//    val navigator = LocalNavigator.currentOrThrow
    Card(
        modifier = Modifier
            .padding(2.dp)
    ) {
        //Name text...
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            //      horizontalArrangement = Arrangement.Start
        ) {
            Column {


                Text(
                    text = "Name",//item.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                //Time
                Text(
                    text = "time",//item.time,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
                //ProgressBar
                LinearProgressIndicator(
                    modifier = Modifier
                        //               .fillMaxWidth()
                        .padding(top = 4.dp),
                    progress = 0.5f
                )

            }
            Row(
                Modifier.weight(0.2F),
                //modifier = Modifier.fillMaxWidth(0.4f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                //////Buttons
                //counter
                Text(
                    text = "1/2",//"${item.allItemCount}/${item.allSelectedItemsCount}",
                    modifier = Modifier
                        //       .background(Color.Red)
                        .padding(5.dp),
                    color = Color.Blue
                )
                //delete button
                IconButton(
                    onClick = {
                        //        onEvent(ShoppingListEvent.OnShowDeleteDialog(item))
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteSweep,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Red)
                            .padding(5.dp),
                        tint = Color.White
                    )
                }
                //edit button
                IconButton(
                    onClick = {
                        //       onEvent(ShoppingListEvent.OnShowEditDialog(item))
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Blue)
                            .padding(5.dp),
                        tint = Color.White
                    )
                }
            }
        }

    }


}


