package com.eacipher.evshopping.add_item_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.eacipher.shopapp.add_item_screen.AddItemEvent
import com.eacipher.shopapp.db.Add_item


@Composable
fun UiAddItem(
    item: Add_item,
    OnEvent: (AddItemEvent) -> Unit
    ) {

    val isCheck = item.isChecked == 1L
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 3.dp)
        .clickable {
            OnEvent(AddItemEvent.OnShowEditDialog(item))
        }
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(checked = isCheck, onCheckedChange = { isChecked ->
                val isChk = if (isChecked) 1L else 0L
                OnEvent(AddItemEvent.OnCheckedChange(item.copy(isChecked = isChk )))
            })
            Text(
                modifier = Modifier.weight(1f).padding(start = 10.dp),
                text = item.name,
                fontSize = 14.sp)
            IconButton(onClick = {
                OnEvent(AddItemEvent.OnDelete(item))
            }) {
                Icon(imageVector = Icons.Default.DeleteForever, contentDescription = "Icon")
            }

        }

    }
}