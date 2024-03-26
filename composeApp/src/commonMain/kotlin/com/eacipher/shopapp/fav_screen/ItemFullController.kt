package com.eacipher.shopapp.fav_screen

import androidx.compose.runtime.MutableState

interface ItemFullController {
    val name: MutableState<String>
    val description: MutableState<String>
    val imageUrl: MutableState<String>
    val openItemFull: MutableState<Boolean>
    fun onItemFullEvent(event: ItemFullEvent)
}