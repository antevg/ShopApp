package com.eacipher.shopapp.fav_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eacipher.ShoppingKMP.shopping_list_screen.ShoppingListViewModel
import com.eacipher.ShoppingKMP.shopping_list_screen.UiShoppingListItem
import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.dialog.MainDialog
import com.eacipher.shopapp.shopping_list_screen.ShoppingListEvent
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavScreen() {
    KoinContext {
        val viewModel: FavViewModel = koinInject()
        val items = viewModel.list//.collectAsState(initial = emptyList())
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Gray,
                        titleContentColor = Color.White),
                    title = {
                        Text("Favs")
                    },
                    navigationIcon = {},
                    actions = {
                        IconButton(onClick = {})
                        {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh",
                                tint = Color.White,
                            )
                        }
                    })
            }
        ) { padding ->

            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.Black),
                columns = StaggeredGridCells.Adaptive(minSize = 180.dp),
                verticalItemSpacing = 10.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                item{
                    Text(text = "First")
                }
                items(items.size) {
                    Text(
                        modifier = Modifier.background(Color.DarkGray),
                        text = items[it].name,
                    )
                    IconButton(onClick = {

                    })
                    {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Refresh",
                            tint = Color.White,
                        )
                    }
                }
            }
          //  MainDialog(dialogController = viewModel)
        }
    }

}