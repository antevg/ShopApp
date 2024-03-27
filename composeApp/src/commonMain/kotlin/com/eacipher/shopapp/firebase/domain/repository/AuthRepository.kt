package com.eacipher.ShoppingKMP.domain.repository

import com.eacipher.ShoppingKMP.domain.model.AppUser

interface AuthRepository {
    suspend fun doRegister(user: AppUser): Boolean
    suspend fun doLogin(email: String, password: String): Boolean
}