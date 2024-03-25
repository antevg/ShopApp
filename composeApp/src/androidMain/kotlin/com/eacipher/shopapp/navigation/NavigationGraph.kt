package com.eacipher.evshopping.navigation
/*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eacipher.evshopping.about_screen.AboutScreen
import com.eacipher.evshopping.note_list_screen.NoteListScreen
import com.eacipher.evshopping.settings_screen.SettingsScreen
import com.eacipher.evshopping.shopping_list_screen.ShoppingListScreen
import com.eacipher.evshopping.utils.Routes


@Composable
fun NavigationGraph(navController: NavHostController, onNavigate: (String) -> Unit) {

    NavHost(navController = navController, startDestination =  Routes.SHOPPING_LIST){
        composable(Routes.SHOPPING_LIST){
            ShoppingListScreen(){route->
                onNavigate(route)
            }
        }
        composable(Routes.NOTE_LIST){
            NoteListScreen()
        }
        composable(Routes.ABOUT){
            AboutScreen()
        }
        composable(Routes.SETTINGS){
            SettingsScreen()
        }
    }
}

 */