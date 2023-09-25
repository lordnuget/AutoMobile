package com.automobile.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCall<T>(
    val success: Boolean,
    val data: T
)
