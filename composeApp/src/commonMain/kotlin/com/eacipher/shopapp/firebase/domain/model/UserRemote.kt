package com.eacipher.ShoppingKMP.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRemote(
    val uid: String,
    val email: String? = null,
    val password: String? = null,
    val registrationDate: String? = null,
)
