package com.eacipher.evshopping.navigation
/*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eacipher.evshopping.about_screen.AboutScreen
import com.eacipher.shopapp.add_item_screen.AddItemScreen
import com.eacipher.evshopping.main_screen.MainScreen
import com.eacipher.evshopping.new_note_screen.NewNoteScreen
import com.eacipher.evshopping.note_list_screen.NoteListScreen
import com.eacipher.evshopping.settings_screen.SettingsScreen
import com.eacipher.evshopping.shopping_list_screen.ShoppingListScreen
import com.eacipher.evshopping.utils.Routes


@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Routes.MAIN_SCREEN){
        composable(Routes.ADD_ITEM + "/{listId}"){
            AddItemScreen()
        }
        composable(Routes.NEW_NOTE){
            NewNoteScreen()
        }
        composable(Routes.MAIN_SCREEN){
            MainScreen(navController)
        }
    }
}

 */