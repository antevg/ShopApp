package com.eacipher.shopapp.fav_screen

import com.eacipher.ShoppingKMP.utils.UiEvent
import com.eacipher.shopapp.repository.Repository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavViewModel: ViewModel(), KoinComponent {
    private val repository: Repository by inject()

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
                list[event.favId].isFav = 1
                viewModelScope.launch {
                }
            }

            is FavEvents.OnItemClick -> {
                event.favId
             //    sendUiEvent(UiEvent.Navigate(event.menuId))
            }
        }
    }



    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }




}