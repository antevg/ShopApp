package com.eacipher.ShoppingKMP.shopping_list_screen

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

import com.eacipher.shopapp.dialog.DialogEvent
import com.eacipher.shopapp.dialog.DialogController
import com.eacipher.ShoppingKMP.utils.UiEvent
import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.repository.Repository
import com.eacipher.shopapp.shopping_list_screen.ShoppingListEvent

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShoppingListViewModel() : ViewModel(), DialogController, KoinComponent {

    private val repository: Repository by inject()

    private var listItem: Shopping_list_name? = null
    val list = repository.getAllItems(viewModelScope.coroutineContext)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText= mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    private val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    private val year = currentDate.year
    private val month =currentDate.monthNumber
    private val day = currentDate.dayOfMonth
    private val date ="$day/$month/$year"


    fun onEvent(event: ShoppingListEvent){
        when(event){
            is ShoppingListEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        Shopping_list_name(
                            id = listItem?.id ?: 0,
                            name = editableText.value,
                            time = date,
                            allItemCount = listItem?.allItemCount ?: 0,
                            allSelectedItemsCount = listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }

            is ShoppingListEvent.OnItemClick -> {
              // sendUiEvent(UiEvent.Navigate(event.listId))

            }

            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item
                openDialog.value = true
                editableText.value = listItem?.name ?: ""
                dialogTitle.value = "List name:"
                showEditableText.value = true
            }
            
            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item
                openDialog.value = true
                dialogTitle.value = "Delete?"
                showEditableText.value = false

            }
        }
    }

    override fun onDialogEvent(event: DialogEvent){
        when (event){
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnConfirm -> {
                if (showEditableText.value){
                    onEvent(ShoppingListEvent.OnItemSave)
                 //   editableText.value = ""
                } else {
                    viewModelScope.launch {
                        listItem?.let { repository.deleteItem(it) }
                    }
                }
                openDialog.value = false
            }
            is DialogEvent.OnTextChanged -> {
                editableText.value = event.text
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}

