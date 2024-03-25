package com.eacipher.shopapp.add_item_screen

import androidx.compose.runtime.mutableStateOf

import com.eacipher.ShoppingKMP.utils.UiEvent

import com.eacipher.shopapp.db.Add_item
import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.dialog.DialogController
import com.eacipher.shopapp.dialog.DialogEvent
import com.eacipher.shopapp.repository.Repository

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class AddItemViewModel(val listId: Int): ViewModel(), DialogController, KoinComponent {

    private val repository: Repository by inject()
 //   var listId = -1
    var itemsList: Flow<List<Add_item>>? = null
    private var addItem: Add_item? = null
    private var shoppingListItem: Shopping_list_name? = null



    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
init {
    itemsList = repository.getAllItemsById(listId, viewModelScope.coroutineContext)
    init()
}
    fun init() {
        viewModelScope.launch {
            shoppingListItem = repository.getListItemById(listId)
   //         shoppingListItem?.id?.toInt()?.let { setListId(it) }

   //         itemsList = repository.getAllItemsById(listId, viewModelScope.coroutineContext)
        }
    }

    private fun setListId(id: Int?){
        if (id != null) {
     //       listId = id
        }
    }



    override var dialogTitle = mutableStateOf("Edit name")
        private set
    override var editableText= mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set
    var itemText = mutableStateOf("")
        private set

    fun onEvent(event: AddItemEvent){
        when (event){
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }
                updateShoppingListCount()
            }
            is AddItemEvent.OnShowEditDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""

            }
            is AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch
                    if (addItem != null){
                        if (addItem!!.name.isEmpty()){
                            sendUiEvent(UiEvent.ShowSnackBar("Name no must be Empty"))
                            return@launch
                        }
                    } else {
                        if (itemText.value.isEmpty()){
                            sendUiEvent(UiEvent.ShowSnackBar("Name no must be Empty"))
                            return@launch
                        }
                    }
                    val tempItem = Add_item(
                        id = addItem?.id ?: -1,
                        name = addItem?.name ?: itemText.value,
                        isChecked = addItem?.isChecked ?: 0,
                        listId = listId.toLong()
                    )
                    repository.insertAddItem(tempItem)
                    itemText.value = ""
                    addItem = null

                }
                updateShoppingListCount()
            }
            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }
            is AddItemEvent.OnCheckedChange ->{
                viewModelScope.launch {
                    repository.insertAddItem(event.item)
                }
                updateShoppingListCount()
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
                openDialog.value = false
                addItem = addItem?.copy(name = editableText.value)
                editableText.value = ""
                onEvent(AddItemEvent.OnItemSave)
            }
            is DialogEvent.OnTextChanged -> {
                editableText.value = event.text
            }
        }
    }

    private fun updateShoppingListCount(){
        viewModelScope.launch {
            itemsList?.collect{ list ->
                var counter = 0
                list.forEach{ item ->
                    if (item.isChecked == 1L) counter++
                }
                shoppingListItem?.let {
                    repository.insertItem(
                        it.copy(
                            allItemCount = list.size.toLong(),
                            allSelectedItemsCount = counter.toLong()
                        ))
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}