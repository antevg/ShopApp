package com.eacipher.shopapp.repository

import com.eacipher.shopapp.db.Add_item
import com.eacipher.shopapp.db.Shopping_list_name
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface Repository {
    suspend fun insertItem(item: Shopping_list_name)
    suspend fun deleteItem(item: Shopping_list_name)
    fun getAllItems(context: CoroutineContext): Flow<List<Shopping_list_name>>

    suspend fun insertAddItem(item: Add_item)
    suspend fun deleteItem(item: Add_item)
    fun getAllItemsById(listId: Int, context: CoroutineContext): Flow<List<Add_item>>
    suspend fun getListItemById(listId: Int): Shopping_list_name

}