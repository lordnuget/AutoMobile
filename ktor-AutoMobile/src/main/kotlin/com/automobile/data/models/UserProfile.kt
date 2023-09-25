package com.automobile.data.models

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserProfile(
    val userProfileID: Int,
    val firstName: String,
    val lastName: String,
    @Serializable(with = DateSerializer::class)
    val dateOfBirth: LocalDate,
    val driversLicenceNumber: Int
)