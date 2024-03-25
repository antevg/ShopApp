package com.eacipher.shopapp

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.BottomAppBar


import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.eacipher.ShoppingKMP.shopping_list_screen.ShoppingListViewModel

import com.eacipher.shopapp.tabs.FavoritesTab
import com.eacipher.shopapp.tabs.HomeTab
import com.eacipher.shopapp.tabs.ProfileTab
import com.eacipher.shopapp.theme.AppTheme
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


@Composable
internal fun App() = AppTheme {


    KoinContext {
        val viewModel: ShoppingListViewModel = koinInject ()
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            TabNavigator(
                HomeTab,
                tabDisposable = {
                    TabDisposable(
                        navigator = it,
                        tabs = listOf(HomeTab, FavoritesTab, ProfileTab)
                    )
                }
            ) { tabNavigator ->
                Scaffold(

                    content = {
                        CurrentTab()
                              },
                    bottomBar = {
                        BottomAppBar (
                            modifier = Modifier.background(Color.Blue)
                        ){
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(FavoritesTab)
                            TabNavigationItem(ProfileTab)
                        }
                    }
                )
            }
        }

    }


}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}


internal expect fun openUrl(url: String?)