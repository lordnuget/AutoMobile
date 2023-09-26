package com.automobile.data.models

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class CarReservation(
    val carReservationID: Int,
    @Serializable(with = DateTimeSerializer::class)
    val dateTimeFrom: LocalDateTime,
    @Serializable(with = DateTimeSerializer::class)
    val dateTimeUntil: LocalDateTime,
    val userProfileID: Int,
    val carID: Int
)
