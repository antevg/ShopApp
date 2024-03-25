package com.eacipher.shopapp

sealed class Screen(val route: String) {
    object ShoppingList : Screen("shopping_list")
    object AboutScreen : Screen("about")
    object Purchase : Screen("purchase")
    object AddPurchase : Screen("add_purchase")
}