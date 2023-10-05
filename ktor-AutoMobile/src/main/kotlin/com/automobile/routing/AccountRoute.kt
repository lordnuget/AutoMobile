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
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

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

        /*
        * Create account
        */


        // Declares a suspending function `createAccount`
        // It takes in two parameters - an email and a password.
        suspend fun createAccount(email: String, password: String) {

            // Initializes a new HTTP client with configuration for JSON serialization.
            val client = HttpClient {
                install(JsonFeature) {
                    // Sets up a JSON serializer using `KotlinxSerializer` from kotlinx.serialization library.
                    serializer = KotlinxSerializer()
                }
            }

            // Defines a local data class `AccountRequest` to represent the account creation request payload.
            data class AccountRequest(val email: String, val password: String)

            try {
                // Attempts to make a POST request to "our api endpoint" using the provided email and password.
                // The result of this request is stored in the `response` variable.
                val response: HttpResponse = client.post("our api endpoint") {
                    // Sets the content type of the request to JSON.
                    contentType(ContentType.Application.Json)
                    // Sets the request body using the `AccountRequest` data class initialized with the given email and password.
                    body = AccountRequest(email, password)
                }

                // Checks the HTTP status code of the response.
                if (response.status == HttpStatusCode.OK) {
                    // If the status code is 200 OK, prints that the account was created successfully.
                    println("Account created successfully!")
                } else if (response.status == HttpStatusCode.Conflict) {
                    // If the status code is 409 Conflict, prints that the account already exists.
                    println("Account already exists!")
                } else {
                    // For any other status code, prints an unexpected error message along with the status code.
                    println("Unexpected error: ${response.status}")
                }
            } catch (e: ClientRequestException) {
                // Catches exceptions that are specific to client requests. Prints the response content.
                println("Error: ${e.response.content}")
            } catch (e: Exception) {
                // Catches any other general exceptions. Prints the error message.
                println("Error: ${e.message}")
            } finally {
                // Closes the HTTP client, releasing any resources it might be using.
                client.close()
            }
        }

        /*
        * Delete account
        */

        // Declares a suspending function `deleteAccount`
        // It takes in a parameter - an email.
        suspend fun deleteAccount(email: String) {

            // Initializes a new HTTP client without any specific configuration.
            val client = HttpClient()

            try {
                // Attempts to make a DELETE request to "our api endpoint" with the given accountID as a parameter.
                // The result of this request is stored in the `response` variable.
                val response: HttpResponse = client.delete("our api endpoint") {
                    // Adds a URL parameter named "accountID" with a value of `accountID` to the request.
                    // Note: The code might have an error since `accountID` is not defined in the function or provided context.
                    parameter("accountID", accountID)
                }

                // Checks the HTTP status code of the response.
                if (response.status == HttpStatusCode.OK) {
                    // If the status code is 200 OK, prints that the account was deleted successfully.
                    println("Account deleted successfully!")
                } else if (response.status == HttpStatusCode.NotFound) {
                    // If the status code is 404 Not Found, prints that the account could not be found.
                    println("Account not found!")
                } else {
                    // For any other status code, prints an unexpected error message along with the status code.
                    println("Unexpected error: ${response.status}")
                }
            } catch (e: ClientRequestException) {
                // Catches exceptions that are specific to client requests. Prints the response content.
                println("Error: ${e.response.content}")
            } catch (e: Exception) {
                // Catches any other general exceptions. Prints the error message.
                println("Error: ${e.message}")
            } finally {
                // Closes the HTTP client, releasing any resources it might be using.
                client.close()
            }
        }



    }
}

private const val BASE_URL = "http://localhost:8080"