package com.eacipher.shopapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Router {
    private val navigationState: MutableStateFlow<Screen> = MutableStateFlow(Screen.ShoppingList)

    val currentScreen: StateFlow<Screen> = navigationState.asStateFlow()

    fun navigateTo(screen: Screen) {
        navigationState.value = screen
    }
}