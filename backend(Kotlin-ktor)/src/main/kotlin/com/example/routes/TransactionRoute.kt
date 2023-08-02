package com.example.routes

import com.example.db.DatabaseConnection
import com.example.entity.AccountEntity
import com.example.entity.CustomerEntity
import com.example.entity.TransactionEntity
import com.example.model.AccountModel
import com.example.model.ResponseModel
import com.example.model.TransactionModel
import com.example.model.UserCredentials
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.transactionRoutes(){
    val db=DatabaseConnection.database
    routing {
        post("/api/transaction/fetch-transactions"){
            val user=call.receive<UserCredentials>()
            val obj=db.from(CustomerEntity).select().where {
                CustomerEntity.id eq user.customerId
            }.map {
                val id=it[CustomerEntity.id]!!
                val password=it[CustomerEntity.password]!!
                UserCredentials(id,password)
            }.firstOrNull()
            if(obj==null){
                call.respond(status = HttpStatusCode.NotFound, message = ResponseModel("Customer not found!"))
            }
            else {
                if(obj.password!=user.password){
                    call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Passwords not matching!"))
                }
                else{
                    val ret=db.from(TransactionEntity).select().where {
                        TransactionEntity.customerId eq user.customerId
                    }.map {
                        val id=it[TransactionEntity.id]!!
                        val customerId=it[TransactionEntity.customerId]!!
                        val amount=it[TransactionEntity.amount]!!
                        val bankId=it[TransactionEntity.bankId]!!
                        val accountId=it[TransactionEntity.accountId]!!
                        val type=it[TransactionEntity.type]!!
                        TransactionModel(id,type,amount,accountId,bankId,customerId)
                    }
                    call.respond(status = HttpStatusCode(200,"ok"), message = ret)
                }
            }
        }
    }
}