package com.eacipher.ShoppingKMP.domain.repository

import com.eacipher.ShoppingKMP.domain.model.UserRemote
import com.eacipher.ShoppingKMP.domain.model.AppUser
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore


class AuthRepositoryImpl: AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore
    override suspend fun doRegister(user: AppUser): Boolean {
        return try {
            val newuser = auth.createUserWithEmailAndPassword(user.email, user.password).user.let { fbUser ->
                if (fbUser != null){
                    //Записываем пользователя так же и в файрстор
                    val fireStoreUser = UserRemote(
                        uid = fbUser.uid,
                        email = user.email,
                        password = user.password,
                        registrationDate = user.registrationDate
                    )
                    firestore.collection("user").document(fbUser.uid).set(fireStoreUser)
                    fbUser
                } else {
                    null
                }
            }
            newuser != null
        } catch (e:Exception){
            false
        }
    }

    override suspend fun doLogin(email: String, password: String): Boolean {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password)
            user.user != null
        } catch (e: Exception){
            false
        }

    }
}