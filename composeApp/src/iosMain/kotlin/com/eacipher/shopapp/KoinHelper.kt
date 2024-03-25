package com.eacipher.shopapp

import com.eacipher.shopapp.di.sharedModule
import org.koin.core.context.startKoin

fun doInitKoin() {
    startKoin {
        modules(sharedModule)
    }
}