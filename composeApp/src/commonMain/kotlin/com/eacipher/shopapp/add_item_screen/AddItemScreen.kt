package com.eacipher.shopapp.add_item_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DoorBack

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

import com.eacipher.ShoppingKMP.utils.UiEvent

import com.eacipher.evshopping.add_item_screen.UiAddItem
import com.eacipher.shopapp.db.Shopping_list_name

import com.eacipher.shopapp.dialog.MainDialog
import com.eacipher.shopapp.shopping_list_screen.ShoppingListEvent
import org.koin.compose.koinInject

@Composable
@OptIn(ExperimentalMaterial3Api::class)


fun AddItemScreen(listId: Int, title: String) {
    val viewModel = AddItemViewModel(listId)// = koinInject()

//    viewModel.listId = listId
    viewModel.init()

    val itemsList = viewModel.itemsList?.collectAsState(initial = emptyList())


        val navigator = LocalNavigator.currentOrThrow


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White)
                ,
                title = {
         //           Text(title)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigator.pop()
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Add",
                            tint = Color.White,
                        )
                    }
                },
                actions = {

                })
        }
    ) { padding->
        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(padding)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = viewModel.itemText.value,
                        onValueChange = { text ->
                            viewModel.onEvent(AddItemEvent.OnTextChange(text))
                        },
                        label = {
                            Text(
                                text = "New Item",
                                fontSize = 12.sp
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            //   backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Gray
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        //viewModel.listId = listId
                        viewModel.onEvent(AddItemEvent.OnItemSave)
                    }) {
                        Icon(
                            imageVector = Icons.Default.AddBox,
                            contentDescription = "Add"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
                    .padding(
                        start = 5.dp,
                        end = 3.dp
                    )
            ) {
                if (itemsList != null) {
                    items(itemsList.value.size) { item ->
                        UiAddItem(item = itemsList.value[item], OnEvent = { event ->
                            viewModel.onEvent(event)
                        })

                    }
                }
            }
        }
        MainDialog(dialogController = viewModel)

    }
}
