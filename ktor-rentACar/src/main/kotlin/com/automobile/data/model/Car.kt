package com.automobile.data.model

import kotlinx.serialization.Serializable
import java.util.Currency

@Serializable
data class Car(
    val carID: Int,
    val userProfileID: Int,
    val licencePlate: String,
    val carBrand: String,
    val vehicleType: String,
    val amountOfPassengers: Int,
    val amountOfDoors: Int,
    val automatic: Boolean,
    val gpsAvailable: Boolean,
    val vehicleLocation: String,
    val carPriceAmount: Double,
    val carPriceCurrency: String
)
