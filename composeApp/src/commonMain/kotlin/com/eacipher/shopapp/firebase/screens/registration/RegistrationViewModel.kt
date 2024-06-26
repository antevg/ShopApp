package com.eacipher.shopapp.firebase.screens.registration

import com.eacipher.ShoppingKMP.domain.model.AppUser
import com.eacipher.ShoppingKMP.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegistrationViewModel : ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RegistrationState()
        )
    private val useCase: AuthUseCase by inject()


    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.OnRegistration -> doRegistration(event)
            is RegistrationEvent.OnEmailChanged -> doEmailChanged(event)
            is RegistrationEvent.OnPasswordChanged -> doPasswordChanged(event)
            is RegistrationEvent.OnSetDefaultState -> setDefaultState()
        }
    }


    private fun doRegistration(event: RegistrationEvent.OnRegistration) {
        when {
            _state.value.emailText.isBlank() || _state.value.emailText.isEmpty() -> {
                _state.update {
                    it.copy(
                        emailError = "Email is blank"
                    )
                }
            }

            _state.value.passwordText.isBlank() || _state.value.passwordText.isEmpty() -> {
                _state.update {
                    it.copy(
                        passwordError = "Password is blank"
                    )
                }
            }

            else -> {
                if (_state.value.isLoading) return

                viewModelScope.launch {
                    _state.value = _state.value.copy(isLoading = true)
                    try {
                        val user = AppUser(
                            email = _state.value.emailText,
                            password = _state.value.passwordText,
                            registrationDate = Clock.System.now()
                                .toLocalDateTime(TimeZone.currentSystemDefault()).toString()
                        )
                        useCase.doRegistration(user) {
                            _state.update {
                                it.copy(
                                    isRegistrationSuccess = true,
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isRegistrationSuccess = false,
                                isLoading = false,
                                errorMessage = e.message ?: "Some error"
                            )
                        }
                        delay(4000)
                        setDefaultState()
                    }
                }
            }
        }
    }

    private fun doPasswordChanged(event: RegistrationEvent.OnPasswordChanged) {
        _state.update {
            it.copy(
                passwordText = event.value,
                passwordError = null
            )
        }
    }

    private fun doEmailChanged(event: RegistrationEvent.OnEmailChanged) {
        _state.update {
            it.copy(
                emailText = event.value,
                emailError = null
            )
        }
    }

    private fun setDefaultState() {
        _state.update {
            it.copy(
                isRegistrationSuccess = false,
                emailText = "",
                passwordText = "",
                emailError = null,
                passwordError = null,
                isLoading = false,
                errorMessage = null
            )
        }
    }


}