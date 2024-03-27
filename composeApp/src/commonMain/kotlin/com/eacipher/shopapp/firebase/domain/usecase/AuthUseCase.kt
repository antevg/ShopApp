package com.eacipher.ShoppingKMP.domain.usecase

import com.eacipher.ShoppingKMP.domain.model.AppUser
import com.eacipher.ShoppingKMP.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthUseCase: KoinComponent {
    private val repository: AuthRepository by inject()

    suspend fun doRegistration(user: AppUser, onSuccess:()-> Unit){
        try {
            repository.doRegister(user).let { isSuccess->
                if (isSuccess){
                    onSuccess()
                } else {
                    throw Exception("Something went wrong")
                }
            }
        } catch (e:Exception){
            throw Exception("Error: ${e.message}")
        }
    }

    suspend fun doLogin(email: String, password: String, onSuccess:()-> Unit){
        try {
            repository.doLogin(email, password).let { isSuccess->
                if (isSuccess){
                    onSuccess()
                } else {
                    throw Exception("Something went wrong")
                }
            }
        } catch (e:Exception){
            throw Exception("Error: ${e.message}")
        }
    }
}