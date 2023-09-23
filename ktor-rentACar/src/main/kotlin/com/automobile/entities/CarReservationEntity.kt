package com.automobile.entities

import org.ktorm.schema.datetime
import org.ktorm.schema.int

object CarReservationEntity: org.ktorm.schema.Table<Nothing>(tableName = "CarReservation") {
    val carReservationID = int(name = "carReservationID").primaryKey()
    val dateTimeFrom = datetime(name = "dateTimeFrom")
    val dateTimeUntil = datetime(name = "dateTimeUntil")
    val userProfileID = int(name = "userProfileID")
    val carID = int(name = "carID")
}