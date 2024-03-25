package com.eacipher.shopapp.add_item_screen

import com.eacipher.shopapp.db.Add_item


sealed class AddItemEvent{
    data class OnDelete(val item: Add_item): AddItemEvent()
    data class OnShowEditDialog(val item: Add_item): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: Add_item): AddItemEvent()
    object OnItemSave: AddItemEvent()
}
