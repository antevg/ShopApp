package com.eacipher.shopapp.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = MyDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.myDatabaseQueries

    //val getItems = dbQuery.getAllItems().asFlow().mapToList()

    internal fun getAllItems(context: CoroutineContext): Flow<List<Shopping_list_name>> {
        return dbQuery.getAllItems().asFlow().mapToList(context)
    }

    internal fun getListItemById(listId: Int): Shopping_list_name {
        return dbQuery.getListItemById(listId.toLong()).executeAsOne()
    }


    fun insertItem(shoppingListName: Shopping_list_name) {
        dbQuery.insertItem(
            // id = shoppingListName.id,
            name = shoppingListName.name,
            time = shoppingListName.time,
            allItemCount = shoppingListName.allItemCount,
            allSelectedItemsCount = shoppingListName.allSelectedItemsCount
        )
    }

    fun replaceItem(shoppingListName: Shopping_list_name) {
        dbQuery.replaceItem(
            id = shoppingListName.id,
            name = shoppingListName.name,
            time = shoppingListName.time,
            allItemCount = shoppingListName.allItemCount,
            allSelectedItemsCount = shoppingListName.allSelectedItemsCount
        )
    }

    fun deleteShoppingList(id: Long) {
        dbQuery.transaction {
            dbQuery.deleteShoppingList(id.toLong())
            dbQuery.deleteAddItems(id.toLong())
        }
    }

    fun getItemsByListId(listId: Int, context: CoroutineContext): Flow<List<Add_item>> {
        return dbQuery.getItemsByListId(listId.toLong()).asFlow().mapToList(context)
    }

    fun insertAddItem(item: Add_item) {
        dbQuery.insertAddItem(
            //    id = item.id,
            name = item.name,
            isChecked = item.isChecked,
            listId = item.listId
        )
    }

    fun replaceAddItem(item: Add_item) {
        dbQuery.replaceAddItem(
            id = item.id,
            name = item.name,
            isChecked = item.isChecked,
            listId = item.listId
        )
    }

    fun deleteItem(id: Long) {
        dbQuery.deleteItem(id.toLong())
    }
}