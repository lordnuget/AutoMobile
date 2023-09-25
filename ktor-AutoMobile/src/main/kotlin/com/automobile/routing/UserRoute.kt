package com.automobile.routing

import com.automobile.data.models.ResponseCall
import com.automobile.data.models.UserProfile
import com.automobile.database.DatabaseConnection
import com.automobile.entities.UserProfileEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.userRoutes() {
    val db = DatabaseConnection.database

    routing {
        // Get User data by carID
        get("/user/{id}") {
            val id = call.parameters["id"]?.toInt() ?: -1
            val user = db.from(UserProfileEntity)
                .select()
                .where { UserProfileEntity.userProfileID eq id }
                .map {
                    val userProfileID = it[UserProfileEntity.userProfileID]!!
                    val firstName = it[UserProfileEntity.firstName]!!
                    val lastName = it[UserProfileEntity.lastName]!!
                    val dateOfBirth = it[UserProfileEntity.dateOfBirth]!!
                    val driversLicenceNumber = it[UserProfileEntity.driversLicenceNumber]!!

                    UserProfile(userProfileID = userProfileID, firstName = firstName, lastName = lastName, dateOfBirth = dateOfBirth, driversLicenceNumber - driversLicenceNumber )
                }.firstOrNull()

            if (user == null) {
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
                        data = user
                    )
                )

            }
        }
    }
}