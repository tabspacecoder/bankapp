package com.example.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CustomerEntity: Table<Nothing>("customers") {
    val id=int("id")
    val name=varchar("name")
    val email=varchar("email")
    val password=varchar("password")
    val bankId=int("bankId")
}