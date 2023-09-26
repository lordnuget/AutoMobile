package com.automobile.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Account (
    val accountID: Int,
    val email: String,
    val password: String,
    val userProfileID: Int
)
