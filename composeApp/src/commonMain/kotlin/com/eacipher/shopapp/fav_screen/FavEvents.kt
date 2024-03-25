package com.eacipher.shopapp.fav_screen

import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.shopping_list_screen.ShoppingListEvent

sealed class FavEvents {
    data class OnItemClick(val favId: Int): FavEvents()
    data class OnAddToFavs(val favId: Int): FavEvents()
}