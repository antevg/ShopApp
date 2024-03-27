package com.eacipher.shopapp.di

import com.eacipher.ShoppingKMP.domain.repository.AuthRepository
import com.eacipher.ShoppingKMP.domain.repository.AuthRepositoryImpl
import com.eacipher.ShoppingKMP.domain.usecase.AuthUseCase
import com.eacipher.ShoppingKMP.shopping_list_screen.ShoppingListViewModel
import com.eacipher.shopapp.db.Database
import com.eacipher.shopapp.db.DatabaseDriverFactory
import com.eacipher.shopapp.fav_screen.FavViewModel
import com.eacipher.shopapp.firebase.screens.login.LoginViewModel
import com.eacipher.shopapp.firebase.screens.registration.RegistrationViewModel

import com.eacipher.shopapp.repository.Repository
import com.eacipher.shopapp.repository.RepositoryImpl
import com.eacipher.shopapp.viewmodels.AddPurchaseViewModel
import com.eacipher.shopapp.viewmodels.PurchaseViewModel


import org.koin.dsl.module

val sharedModule = module {
    single { DatabaseDriverFactory() }
    single { Database(get()) }
    single<Repository> { RepositoryImpl(get()) }

    single { ShoppingListViewModel() }
    single { PurchaseViewModel(get()) }
    single { AddPurchaseViewModel(get()) }
    single { FavViewModel() }

    factory { AuthUseCase() }
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
    single { RegistrationViewModel() }
    single { LoginViewModel() }

}



