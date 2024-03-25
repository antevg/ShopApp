package com.eacipher.shopapp

import com.eacipher.shopapp.db.Database
import com.eacipher.shopapp.db.DatabaseDriverFactory
import com.eacipher.shopapp.db.Shopping_list_name

class ShoppingSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)

  //  @Throws(Exception::class) suspend fun getLaunches(forceReload: Boolean): List<Shopping_list_name> {
 //       return database.getAllItems()
 //   }
}