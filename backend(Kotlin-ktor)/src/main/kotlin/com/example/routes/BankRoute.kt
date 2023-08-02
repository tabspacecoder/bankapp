package com.example.routes

import com.example.db.DatabaseConnection
import com.example.entity.BankEntity
import com.example.model.BankModel
import com.example.model.CreateBankModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.bankRoutes(){
    val db=DatabaseConnection.database
    routing {
        post("/api/bank/fetch-banks"){
            val res=db.from(BankEntity).select().map {
                val id=it[BankEntity.id]!!
                val name=it[BankEntity.name]!!
                BankModel(id,name)
            }
            call.respond(status = HttpStatusCode.OK, message = res)
        }
        post("/api/bank/create-bank") {
            val bank=call.receive<CreateBankModel>()
            val res=db.insert(BankEntity){
                set(BankEntity.name,bank.name)
            }
            val ret=db.from(BankEntity).select().where{
                BankEntity.name eq bank.name
            }.map {
                val id=it[BankEntity.id]!!
                val name=it[BankEntity.name]!!
                BankModel(id,name)
            }
            call.respond(status = HttpStatusCode.OK, message = ret)
        }

    }
}