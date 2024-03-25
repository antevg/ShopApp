package com.eacipher.shopapp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddItem(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String,
    @SerialName("ischecked")
    val isChecked: Boolean,
    @SerialName("listid")
    val listId: Int
)

@Serializable
data class ShoppingListItem(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String,
    @SerialName("time")
    val time: String,
    @SerialName("all_item_count")
    val allItemCount: Int,
    @SerialName("all_selected_item_count")
    val allSelectedItemsCount: Int
) //: java.io.Serializable
