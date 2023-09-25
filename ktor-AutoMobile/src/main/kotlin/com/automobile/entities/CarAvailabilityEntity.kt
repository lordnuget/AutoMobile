package com.automobile.entities

import org.ktorm.schema.datetime
import org.ktorm.schema.int

object CarAvailabilityEntity: org.ktorm.schema.Table<Nothing>(tableName = "CarAvailability") {
    val carAvailabilityID = int(name = "carAvailabilityID").primaryKey()
    val dateTimeFrom = datetime(name = "dateTimeFrom")
    val dateTimeUntil = datetime(name = "dateTimeUntil")
    val carID = int(name = "carID")
}