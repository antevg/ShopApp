package com.eacipher.shopapp.dialog

sealed class DialogEvent{
    data class OnTextChanged(val text: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}
