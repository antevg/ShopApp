package com.eacipher.shopapp.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.eacipher.shopapp.AndroidApp

actual class DatabaseDriverFactory() {
    actual fun createDriver(): SqlDriver {
        val context = AndroidApp.INSTANCE
        return AndroidSqliteDriver(MyDatabase.Schema, context, "test.db")
    }
}