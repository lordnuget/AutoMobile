package com.automobile.routing

import com.automobile.data.models.Car //CBakker
import com.automobile.data.models.CarAvailablilty
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.CarAvailabilityEntity
import com.automobile.entities.CarEntity //Cbakker
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.* //CBakker
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
        post("/carAvailability/{CarID}"){
            val id = call.parameters["CarID"]?.toInt() ?: -1
            val request = call.receive<CarAvailablilty>()
            val result =  db.insert(CarAvailabilityEntity){
//                                        set(it.carID, request.carID)
                set(it.carID, id)
                set(it.carAvailabilityID, request.carAvailabilityID)
                set(it.dateTimeFrom, request.dateTimeFrom)
                set(it.dateTimeUntil, request.dateTimeUntil)

            }
            if (result == 1){
                //succes
                call.respond(HttpStatusCode.OK, ResponseCall(
                    success = true,
                    data = "Gegevens met succes opgeslagen"
                ))
            }else{
                //false
                call.respond(HttpStatusCode.BadRequest, ResponseCall(
                    success = false,
                    data =  "Sorry er ging wat mis bij het opslaan"
                ))
            }
        }
    }
}

private const val BASE_URL = "http://localhost:8080"