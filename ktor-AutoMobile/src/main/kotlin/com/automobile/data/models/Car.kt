package com.automobile.data.models

import kotlinx.serialization.Serializable

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
    val carPriceAmount: Double,
    val carPriceCurrency: String
)
