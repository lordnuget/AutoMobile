package com.automobile.routing

import com.automobile.data.models.CarAvailablilty
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.CarAvailabilityEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import org.ktorm.dsl.*

fun Application.carAvailabilityRoutes() {
    val db = DatabaseConnection.database

    /*
     * Get CarAvailability data by carAvailabilityID
     */
    routing {
        get("/carAvailability/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val carAvailability = db.from(CarAvailabilityEntity)
                .select()
                .where { CarAvailabilityEntity.carAvailabilityID eq id }
                .map {
                    val carAvailabilityID = it[CarAvailabilityEntity.carAvailabilityID]!!
                    val dateTimeFrom = it[CarAvailabilityEntity.dateTimeFrom]!!
                    val dateTimeUntil = it[CarAvailabilityEntity.dateTimeUntil]!!
                    val carID = it[CarAvailabilityEntity.carID]!!
                    CarAvailablilty(carAvailabilityID = carAvailabilityID, dateTimeFrom = dateTimeFrom, dateTimeUntil = dateTimeUntil, carID = carID)
                }.firstOrNull()

            if (carAvailability == null) {
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
                        data = carAvailability
                    )
                )

            }
        }
    }
}

private const val BASE_URL = "http://localhost:8080"