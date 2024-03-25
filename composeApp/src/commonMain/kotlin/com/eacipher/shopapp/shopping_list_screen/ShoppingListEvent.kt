package com.eacipher.shopapp.shopping_list_screen

import com.eacipher.shopapp.db.Shopping_list_name

sealed class ShoppingListEvent{
    data class OnShowDeleteDialog(val item: Shopping_list_name): ShoppingListEvent()
    data class OnShowEditDialog(val item: Shopping_list_name): ShoppingListEvent()
    data class OnItemClick(val listId: Long): ShoppingListEvent()
    object OnItemSave: ShoppingListEvent()

}
