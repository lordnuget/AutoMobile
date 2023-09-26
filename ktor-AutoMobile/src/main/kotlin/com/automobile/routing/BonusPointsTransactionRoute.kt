package com.automobile.routing

import com.automobile.data.models.BonusPointsTransaction
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.BonusPointsTransactionEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import org.ktorm.dsl.*

fun Application.bonusPointsTransactionRoutes() {
    val db = DatabaseConnection.database

    /*
     * Get BonusPointsTransaction data by bonusPointsTransactionID
     */
    routing {
        get("/bonusPointsTransaction/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val bonusPointsTransaction = db.from(BonusPointsTransactionEntity)
                .select()
                .where { BonusPointsTransactionEntity.bonusPointsTransactionID eq id }
                .map {
                    val bonusPointsTransactionID = it[BonusPointsTransactionEntity.bonusPointsTransactionID]!!
                    val amount = it[BonusPointsTransactionEntity.amount]!!
                    val userProfileID = it[BonusPointsTransactionEntity.userProfileID]!!
                    val carReservationID = it[BonusPointsTransactionEntity.carReservationID]!!
                    BonusPointsTransaction(bonusPointsTransactionID = bonusPointsTransactionID, amount = amount, userProfileID = userProfileID, carReservationID = carReservationID)
                }.firstOrNull()

            if (bonusPointsTransaction == null) {
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
                        data = bonusPointsTransaction
                    )
                )

            }
        }
    }
}

private const val BASE_URL = "http://localhost:8080"