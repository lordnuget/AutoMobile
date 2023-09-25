package com.automobile.database

import org.ktorm.database.Database

object DatabaseConnection {
    val database = Database.connect(
        url = "jdbc:mysql://localhost:3306/automobile",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root"
    )
}