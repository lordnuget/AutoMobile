package com.automobile.routing

import com.automobile.data.models.Account
import com.automobile.data.models.ResponseCall
import com.automobile.database.DatabaseConnection
import com.automobile.entities.AccountEntity
import com.automobile.entities.BonusPointsTransactionEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import org.ktorm.dsl.*

fun Application.accountRoutes() {
    val db = DatabaseConnection.database

    /*
     * Get Account data by accountID
     */
    routing {
        get("/account/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val account = db.from(AccountEntity)
                .select()
                .where { BonusPointsTransactionEntity.bonusPointsTransactionID eq id }
                .map {
                    val accountID = it[AccountEntity.accountID]!!
                    val email = it[AccountEntity.email]!!
                    val password = it[AccountEntity.password]!!
                    val userProfileID = it[AccountEntity.userProfileID]!!
                    Account(accountID = accountID, email = email, password = password, userProfileID = userProfileID)
                }.firstOrNull()

            if (account == null) {
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
                        data = account
                    )
                )

            }
        }
    }
}

private const val BASE_URL = "http://localhost:8080"