package com.automobile.data.models

import com.automobile.data.models.serializers.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Payment (
    val paymentID: Int,
    val amount: Double,
    val currency: String,
    val paymentMethod: String,
    @Serializable(with = DateTimeSerializer::class)
    val dateTime: LocalDateTime,
    val carReservationID: Int
)
