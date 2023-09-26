package com.automobile.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BonusPointsTransaction(
    val bonusPointsTransactionID: Int,
    val amount: Int,
    val userProfileID: Int,
    val carReservationID: Int,
)