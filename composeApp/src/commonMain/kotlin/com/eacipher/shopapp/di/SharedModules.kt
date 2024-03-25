package com.eacipher.shopapp.di

import com.eacipher.ShoppingKMP.shopping_list_screen.ShoppingListViewModel
import com.eacipher.shopapp.db.Database
import com.eacipher.shopapp.db.DatabaseDriverFactory
import com.eacipher.shopapp.fav_screen.FavViewModel

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
}



