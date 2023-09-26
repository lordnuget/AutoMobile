package com.automobile.routing

import com.automobile.data.models.Payment
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.PaymentEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.paymentRoutes() {
    val db = DatabaseConnection.database

    /*
     * Get Payment data by paymentID
     */
    routing {
        get("/payment/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val payment = db.from(PaymentEntity)
                .select()
                .where { PaymentEntity.paymentID eq id }
                .map {
                    val paymentID = it[PaymentEntity.paymentID]!!
                    val amount = it[PaymentEntity.amount]!!
                    val currency = it[PaymentEntity.currency]!!
                    val paymentMethod = it[PaymentEntity.paymentMethod]!!
                    val dateTime = it[PaymentEntity.dateTime]!!
                    val carReservationID = it[PaymentEntity.carReservationID]!!
                    Payment(paymentID = paymentID, amount = amount, currency = currency, paymentMethod = paymentMethod, dateTime = dateTime, carReservationID = carReservationID)
                }.firstOrNull()

            if (payment == null) {
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
                        data = payment
                    )
                )

            }
        }
    }
}