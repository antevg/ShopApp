package com.eacipher.shopapp.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.eacipher.ShoppingKMP.shopping_list_screen.ShoppingListViewModel
import com.eacipher.ShoppingKMP.shopping_list_screen.UiShoppingListItem
import com.eacipher.ShoppingKMP.utils.UiEvent
import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.dialog.MainDialog
import com.eacipher.shopapp.tabs.HomeTab
import kotlinx.coroutines.flow.collect
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


//class ShoppingListScreen : Screen {
//    @Composable
//    override fun Content() {

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen() {
    KoinContext {
        val viewModel: ShoppingListViewModel = koinInject()
        val itemsList = viewModel.list.collectAsState(initial = emptyList())

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White),
                    title = {
                        Text("Shopping Lists")
                    },
                    navigationIcon = {},
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(
                                    ShoppingListEvent.OnShowEditDialog(
                                        Shopping_list_name(
                                            id = -1,
                                            name = "",
                                            time = "",
                                            allItemCount = 0,
                                            allSelectedItemsCount = 0
                                        )))
                            }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White,
                            )
                        }
                    })
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.Black),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(itemsList.value) {
                    UiShoppingListItem(it) { event ->
                        viewModel.onEvent(event)
                    }
                }
            }
            MainDialog(dialogController = viewModel)
        }
    }


}



