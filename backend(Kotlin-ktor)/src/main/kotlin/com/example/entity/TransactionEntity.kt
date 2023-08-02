package com.example.entity

import org.ktorm.schema.Table
import org.ktorm.schema.float
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object TransactionEntity: Table<Nothing>("transactions") {
    val id=int("id")
    val amount=float("amount")
    val type=varchar("type")
    val accountId=int("accountId")
    val bankId=int("bankId")
    val customerId=int("customerId")
}