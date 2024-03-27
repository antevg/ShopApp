package com.eacipher.shopapp.firebase.screens.registration

sealed interface RegistrationEvent {
    data object OnRegistration: RegistrationEvent

    data class OnEmailChanged(val value:String): RegistrationEvent
    data class OnPasswordChanged(val value:String): RegistrationEvent
    data object OnSetDefaultState: RegistrationEvent

}