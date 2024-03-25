package com.eacipher.shopapp.fav_screen

data class Fav(
    val id: Int,
    var name: String,
    var description: String? = null,
    var date_add: String? = null,
    var isFav: Int = 0
)
