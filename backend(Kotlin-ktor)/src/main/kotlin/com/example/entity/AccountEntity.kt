package com.example.entity

import org.ktorm.schema.Table
import org.ktorm.schema.float
import org.ktorm.schema.int

object AccountEntity: Table<Nothing>("accounts") {
    val id=int("id")
    val balance=float("balance")
    val customerId=int("customerId")
    val bankId=int("bankId")
}