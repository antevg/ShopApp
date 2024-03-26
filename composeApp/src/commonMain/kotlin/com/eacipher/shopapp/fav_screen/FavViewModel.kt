package com.eacipher.shopapp.fav_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.eacipher.ShoppingKMP.utils.UiEvent
import com.eacipher.shopapp.dialog.DialogEvent
import com.eacipher.shopapp.repository.Repository
import com.eacipher.shopapp.shopping_list_screen.ShoppingListEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavViewModel(
) : ViewModel(), ItemFullController,  KoinComponent {
    private val repository: Repository by inject()

    override var name = mutableStateOf("")
        private set
    override var description = mutableStateOf("")
        private set
    override var imageUrl = mutableStateOf("")
        private set
    override var openItemFull = mutableStateOf(false)
        private set

    private var item: Fav? = null
    var list = //repository.getAllItems(viewModelScope.coroutineContext)
        listOf<Fav>(
            Fav(1,"name1 sadfgsd sdgsdgsdgs sdgasgsdagsadgsadg sadgsdgdsagsdag sgsdgasdgsadgsad sadgsadgsadgsad sadgasdgsag sdgasdgasdg"),
            Fav(2,"name2"),
            Fav(3,"name3"),
            Fav(4,"name4"),
            Fav(5,"name5"),
        )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: FavEvents){
        when(event){
            is FavEvents.OnAddToFavs -> {
                list[event.fav.id].isFav = 1
                viewModelScope.launch {
                }
            }

            is FavEvents.OnItemClick -> {
                name.value = event.fav.name
                description.value = event.fav.description.toString()
                imageUrl.value = ""
                openItemFull.value = true
             //    sendUiEvent(UiEvent.Navigate(event.menuId))
            }
        }
    }

    override fun onItemFullEvent(event: ItemFullEvent){
        when (event){
                is ItemFullEvent.onClose -> {
                    openItemFull.value = false
                    name.value = "11"
                    description.value = ""
                    imageUrl.value = ""
                }

            }

    }



    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }




}