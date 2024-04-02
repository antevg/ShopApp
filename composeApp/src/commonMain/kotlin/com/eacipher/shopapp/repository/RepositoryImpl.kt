package com.eacipher.shopapp.repository

import com.eacipher.shopapp.db.Add_item
import com.eacipher.shopapp.db.Database
import com.eacipher.shopapp.db.Shopping_list_name
import com.eacipher.shopapp.entity.AddItem
import com.eacipher.shopapp.firebase.domain.model.FavItem
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
//import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(private val dao: Database) : Repository, KoinComponent {


    suspend fun getData(): List<AddItem> {
        val db = Firebase.firestore
        try {
            val getValue =
                db.collection("items").orderBy("id", Direction.DESCENDING).get()
            return getValue.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }


    suspend fun insertFav(item: FavItem) {


        val db = Firebase.firestore
        db.collection("favs")
            .document(item.id.toString())
            .set(item)
    }


    override suspend fun insertItem(item: Shopping_list_name) {
        dao.replaceItem(item)

        insertFav(
            FavItem(
                id = 12,
                name = item.name
            )
        )

        val serItem = AddItem(
            id = item.id.toInt(),
            name = item.name,
            isChecked = true,
            listId = 1
        )


        val db = Firebase.firestore

   //     val resp = db.collection("items")

        val taskData = HashMap<String, Any>()
        taskData["item"] = serItem


        db.collection("items")
            .document(item.id.toString())
            .set(serItem)
        //  .set(taskData)
        // .add(taskData)

        val getValue =
            db.collection("items")
                .get()

        val list = getValue.documents
        list.forEach {
            println(it.data<AddItem>().toString())
            println(it.id)
        }
        val item0 = list[0].data<AddItem>()
        println(item0.toString())



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