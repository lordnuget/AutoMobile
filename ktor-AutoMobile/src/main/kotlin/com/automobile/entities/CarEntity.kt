package com.automobile.entities

import com.automobile.data.models.Car
import com.mysql.cj.xdevapi.Table
import org.ktorm.entity.Entity
import org.ktorm.schema.boolean
import org.ktorm.schema.double
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CarEntity: org.ktorm.schema.Table<Nothing>(tableName = "Car") {
    val carID = int(name = "carID").primaryKey()
    val userProfileID = int(name = "userProfileID")
    val licencePlate = varchar(name = "licencePlate")
    val carBrand = varchar(name = "carBrand")
    val vehicleType = varchar(name = "vehicleType")
    val amountOfPassengers = int(name = "amountOfPassengers")
    val amountOfDoors = int(name = "amountOfDoors")
    val automatic = boolean(name = "automatic")
    val gpsAvailable = boolean(name = "gpsAvailable")
    val carPriceAmount = double(name = "carPriceAmount")
    val carPriceCurrency = varchar(name = "carPriceCurrency")
}

