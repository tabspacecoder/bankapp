package com.example.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object BankEntity: Table<Nothing>("banks") {
    val id=int("id")
    val name=varchar("name")


}