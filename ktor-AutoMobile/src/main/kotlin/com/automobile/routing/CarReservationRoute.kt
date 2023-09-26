package com.automobile.routing

import com.automobile.data.models.CarReservation
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.CarReservationEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import org.ktorm.dsl.*

fun Application.carReservationRoutes() {
    val db = DatabaseConnection.database

    /*
     * Get CarReservation data by carReservationID
     */
    routing {
        get("/carReservation/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val carReservation = db.from(CarReservationEntity)
                .select()
                .where { CarReservationEntity.carReservationID eq id }
                .map {
                    val carReservationID = it[CarReservationEntity.carReservationID]!!
                    val dateTimeFrom = it[CarReservationEntity.dateTimeFrom]!!
                    val dateTimeUntil = it[CarReservationEntity.dateTimeUntil]!!
                    val userProfileID = it[CarReservationEntity.userProfileID]!!
                    val carID = it[CarReservationEntity.carID]!!
                   CarReservation(carReservationID = carReservationID, dateTimeFrom = dateTimeFrom, dateTimeUntil = dateTimeUntil, userProfileID = userProfileID, carID = carID)
                }.firstOrNull()

            if (carReservation == null) {
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
                        data = carReservation
                    )
                )

            }
        }
    }
}

private const val BASE_URL = "http://localhost:8080"