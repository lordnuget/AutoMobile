package com.automobile.entities

import org.ktorm.schema.int

object BonusPointsTransactionEntity: org.ktorm.schema.Table<Nothing>(tableName = "BonusPointsTransaction") {
    val bonusPointsTransactionID = int(name = "bonusPointsTransactionID").primaryKey()
    val amount = int(name = "amount")
    val userProfileID = int(name = "userProfileID")
    val carReservationID = int(name = "carReservationID")
}