package com.eacipher.shopapp.repository

import com.eacipher.shopapp.db.Add_item
import com.eacipher.shopapp.db.Database
import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.entity.AddItem
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(private val dao: Database) : Repository, KoinComponent {


    override suspend fun insertItem(item: Shopping_list_name) {
        dao.replaceItem(item)

        val db = Firebase.firestore
        val serItem  = AddItem(
            id = item.id.toInt(),
            name = item.name,
            isChecked = true,
            listId = 1
        )
        db.collection("items")
            .add(serItem)
    }


    override suspend fun deleteItem(item: Shopping_list_name) {
        dao.deleteShoppingList(item.id)

    }

    override suspend fun insertAddItem(item: Add_item) {
        if (item.id == -1L)
            dao.insertAddItem(item)
        else
            dao.replaceAddItem(item)



    }

    override suspend fun deleteItem(item: Add_item) {
        dao.deleteItem(item.id)
    }

    override fun getAllItems(context: CoroutineContext): Flow<List<Shopping_list_name>> {
        return dao.getAllItems(context)
    }

    override fun getAllItemsById(listId: Int, context: CoroutineContext): Flow<List<Add_item>> {
        return dao.getItemsByListId(listId, context)
    }

    override suspend fun getListItemById(listId: Int): Shopping_list_name {
        return dao.getListItemById(listId)
    }
}