package com.automobile.routes

import com.automobile.data.model.Car
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val BASE_URL = "http://localhost:8080"

private val car = listOf(
    Car(carID = 1, userProfileID = 1, licencePlate = "AB-CE-DF", carBrand = "Audi", vehicleType = "A8", amountOfPassengers = 5, amountOfDoors = 5, automatic = true, gpsAvailable = true, vehicleLocation = "data", carPriceAmount = 12.00, carPriceCurrency = "Euro"),
    Car(carID = 2, userProfileID = 2, licencePlate = "AB-CE-DF", carBrand = "Audi", vehicleType = "A8", amountOfPassengers = 5, amountOfDoors = 5, automatic = true, gpsAvailable = true, vehicleLocation = "data", carPriceAmount = 12.00, carPriceCurrency = "Euro"),
    Car(carID = 3, userProfileID = 3, licencePlate = "AB-CE-DF", carBrand = "Audi", vehicleType = "A8", amountOfPassengers = 5, amountOfDoors = 5, automatic = true, gpsAvailable = true, vehicleLocation = "data", carPriceAmount = 12.00, carPriceCurrency = "Euro")
)

fun Route.getCar() {
    get("/getCar") {
        call.respond(
            HttpStatusCode.OK,
            car.random()
        )
    }
}
