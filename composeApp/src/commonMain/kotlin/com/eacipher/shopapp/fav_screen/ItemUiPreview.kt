package com.eacipher.shopapp.fav_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter


@Composable
fun ItemUiPreview(item: Fav, onEvent: (FavEvents) -> Unit) {

    val painter =
        rememberImagePainter("https://firebasestorage.googleapis.com/v0/b/eadiary-5630a.appspot.com/o/main%2FKbufMZdoeRP559F8l8eoeSbdvLv1%2Fimage_1704038587169?alt=media&token=c70a89ef-c3b9-43d0-b56b-c22fd238ff7e")
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
                .clickable {

                }
        ) {
            Column {
                //Photo

                Image(
                    painter = painter,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .background(Color.Blue)
                        .clickable {
                            onEvent(FavEvents.OnItemClick(item))
                        }

                )

                Text(
                    text = item.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    maxLines = 2
                )
                //Time
                Text(
                    text = item.date_add ?: "",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

        }
    }

}