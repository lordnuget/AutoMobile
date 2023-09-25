package com.automobile.entities

import org.ktorm.schema.datetime
import org.ktorm.schema.double
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object PaymentEntity: org.ktorm.schema.Table<Nothing>(tableName = "Payment") {
    val paymentID = int(name = "paymentID").primaryKey()
    val amount = double(name = "amount")
    val currency = varchar(name = "currency")
    val paymentMethod = varchar(name = "paymentMethod")
    val dateTime = datetime(name = "dateTime")
    val carReservationID = int(name = "carReservationID")
}