package com.automobile.routing

import com.automobile.data.models.Car
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.CarEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import kotlinx.css.datalist
import org.ktorm.dsl.*

fun Application.carRoutes() {
    val db = DatabaseConnection.database

    routing {
        // Get Car data by carID
        get("/car/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val car = db.from(CarEntity)
                .select()
                .where { CarEntity.carID eq id }
                .map {
                    val carID = it[CarEntity.carID]!!
                    val userProfileID = it[CarEntity.userProfileID]!!
                    val licencePlate = it[CarEntity.licencePlate]!!
                    val carBrand = it[CarEntity.carBrand]!!
                    val vehicleType = it[CarEntity.vehicleType]!!
                    val amountOfPassengers = it[CarEntity.amountOfPassengers]!!
                    val amountOfDoors = it[CarEntity.amountOfDoors]!!
                    val automatic = it[CarEntity.automatic]!!
                    val gpsAvailable = it[CarEntity.gpsAvailable]!!
                    val carPriceAmount = it[CarEntity.carPriceAmount]!!
                    val carPriceCurrency = it[CarEntity.carPriceCurrency]!!
                    Car(carID = carID, userProfileID = userProfileID, licencePlate = licencePlate, carBrand = carBrand, vehicleType = vehicleType, amountOfPassengers = amountOfPassengers, amountOfDoors = amountOfDoors, automatic = automatic, gpsAvailable = gpsAvailable, carPriceAmount = carPriceAmount, carPriceCurrency = carPriceCurrency)
                }.firstOrNull()

            if (car == null) {
                call.respond (
                    HttpStatusCode.NotFound,
                    ResponseCall (
                        success = false,
                        data = null
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.OK,
                    ResponseCall (
                        success = true,
                        data = car
                    )
                )

                }
            }
        }
    }


private const val BASE_URL = "http://localhost:8080"


