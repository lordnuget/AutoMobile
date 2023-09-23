package com.automobile.entities

import com.mysql.cj.xdevapi.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserProfileEntity: org.ktorm.schema.Table<Nothing>(tableName = "UserProfile") {
    val userProfileID = int(name = "userProfileID").primaryKey()
    val firstName = varchar(name = "firstName")
    val lastName = varchar(name = "lastName")
    val dateOfBirth = date(name = "dateOfBirth")
    val driversLicenceNumber = int(name = "driversLicenceNumber")
}