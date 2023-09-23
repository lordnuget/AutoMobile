package com.automobile.entities

import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AccountEntity: org.ktorm.schema.Table<Nothing>(tableName = "Account") {
    val accountID = int(name = "accountID").primaryKey()
    val email = varchar(name = "email")
    val password = varchar(name = "password")
    val userProfileID = int(name = "userProfileID")
}