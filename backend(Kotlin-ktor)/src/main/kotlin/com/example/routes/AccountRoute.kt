package com.example.routes

import com.example.db.DatabaseConnection
import com.example.entity.AccountEntity
import com.example.entity.CustomerEntity
import com.example.entity.TransactionEntity
import com.example.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.accountRoutes(){
    val db=DatabaseConnection.database
    routing {
        post("/api/account/create-account"){
            val acc=call.receive<CreateAccountModel>()
            val obj=db.from(CustomerEntity).select().where {
                CustomerEntity.id eq acc.customerId
            }.map {
                val id=it[CustomerEntity.id]!!
                val password=it[CustomerEntity.password]!!
                UserCredentials(id,password)
            }.firstOrNull()
            if(obj==null){
                call.respond(status = HttpStatusCode.NotFound, message = ResponseModel("Customer not found!"))
            }
            else {
                if(obj.password!=acc.password){
                    call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Passwords not matching!"))
                }
                else{
                    val user=db.insert(AccountEntity){
                        set(AccountEntity.balance,acc.balance)
                        set(AccountEntity.customerId,acc.customerId)
                        set(AccountEntity.bankId,acc.bankId)
                    }
                    call.respond(status = HttpStatusCode.OK,message = ResponseModel("Account created successfully"))

                }
            }


        }
        post("/api/account/fetch-accounts"){
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
                    val ret=db.from(AccountEntity).select().where {
                        AccountEntity.customerId eq user.customerId
                    }.map {
                        val id=it[AccountEntity.id]!!
                        val customerId=it[AccountEntity.customerId]!!
                        val balance=it[AccountEntity.balance]!!
                        val bankId=it[AccountEntity.bankId]!!
                        AccountModel(id,balance,customerId,bankId)
                    }
                    call.respond(status = HttpStatusCode(200,"ok"), message = ret)
                }
            }
        }
        post("/api/account/deposit-amount"){
            val req=call.receive<MoneyModel>()
            val obj=db.from(CustomerEntity).select().where {
                CustomerEntity.id eq req.customerId
            }.map {
                val id=it[CustomerEntity.id]!!
                val password=it[CustomerEntity.password]!!
                UserCredentials(id,password)
            }.firstOrNull()
            if(obj==null){
                call.respond(status = HttpStatusCode.NotFound, message = ResponseModel("Customer not found!"))
            }
            else {
                if(obj.password!=req.password){
                    call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Passwords not matching!"))
                }
                else{
                    val acc=db.from(AccountEntity).select().where {
                        AccountEntity.customerId eq req.customerId
                    }.map {
                        val id=it[AccountEntity.id]!!
                        val customerId=it[AccountEntity.customerId]!!
                        val balance=it[AccountEntity.balance]!!
                        val bankId=it[AccountEntity.bankId]!!
                        AccountModel(id,balance,customerId,bankId)
                    }.firstOrNull()!!
//                    if (acc != null) {
//
//
//                    }
                    acc.balance+=req.amount
                    val res=db.update(AccountEntity){
                        set(it.balance,acc.balance)
                        where {
                            it.id eq acc.id
                        }
                    }
                    val tran=db.insert(TransactionEntity){
                        set(it.amount,req.amount)
                        set(it.type,"CREDIT")
                        set(it.accountId,acc.id)
                        set(it.customerId,obj.customerId)
                        set(it.bankId,acc.bankId)
                    }
                    call.respond(status = HttpStatusCode.OK, message = ResponseModel("Successfully Deposited!"))


                }
            }
        }
        post("/api/account/withdraw-amount"){
            val req=call.receive<MoneyModel>()
            val obj=db.from(CustomerEntity).select().where {
                CustomerEntity.id eq req.customerId
            }.map {
                val id=it[CustomerEntity.id]!!
                val password=it[CustomerEntity.password]!!
                UserCredentials(id,password)
            }.firstOrNull()
            if(obj==null){
                call.respond(status = HttpStatusCode.NotFound, message = ResponseModel("Customer not found!"))
            }
            else {
                if(obj.password!=req.password){
                    call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Passwords not matching!"))
                }
                else{
                    val acc=db.from(AccountEntity).select().where {
                        AccountEntity.customerId eq req.customerId
                    }.map {
                        val id=it[AccountEntity.id]!!
                        val customerId=it[AccountEntity.customerId]!!
                        val balance=it[AccountEntity.balance]!!
                        val bankId=it[AccountEntity.bankId]!!
                        AccountModel(id,balance,customerId,bankId)
                    }.firstOrNull()!!
                    if(acc.balance<req.amount){
                        call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Insufficient balance!"))
                    }
                    else{
                        acc.balance-=req.amount
                        val res=db.update(AccountEntity){
                            set(it.balance,acc.balance)
                            where {
                                it.id eq acc.id
                            }
                        }
                        val tran=db.insert(TransactionEntity){
                            set(it.amount,req.amount)
                            set(it.type,"DEBIT")
                            set(it.accountId,acc.id)
                            set(it.customerId,obj.customerId)
                            set(it.bankId,acc.bankId)
                        }
                        call.respond(status = HttpStatusCode.OK, message = ResponseModel("Successfully Withdrawed!"))
                    }

                }
            }
        }
        post("/api/account/transfer-amount"){
            val req=call.receive<TransferModel>()
            val obj=db.from(CustomerEntity).select().where {
                CustomerEntity.id eq req.customerId
            }.map {
                val id=it[CustomerEntity.id]!!
                val password=it[CustomerEntity.password]!!
                UserCredentials(id,password)
            }.firstOrNull()
            if(obj==null){
                call.respond(status = HttpStatusCode.NotFound, message = ResponseModel("Customer not found!"))
            }
            else {
                if(obj.password!=req.password){
                    call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Passwords not matching!"))
                }
                else{
                    val wacc=db.from(AccountEntity).select().where {
                        AccountEntity.id eq req.withdrawAccountId
                    }.map {
                        val id=it[AccountEntity.id]!!
                        val customerId=it[AccountEntity.customerId]!!
                        val balance=it[AccountEntity.balance]!!
                        val bankId=it[AccountEntity.bankId]!!
                        AccountModel(id,balance,customerId,bankId)
                    }.firstOrNull()
                    if(wacc==null){
                        call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Withdraw account not found!"))
                    }
                    else{
                        if(wacc.balance<req.amount){
                            call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Insufficient balance!"))
                        }
                        else{
                            val dacc=db.from(AccountEntity).select().where {
                                AccountEntity.id eq req.depositAccountId
                            }.map {
                                val id=it[AccountEntity.id]!!
                                val customerId=it[AccountEntity.customerId]!!
                                val balance=it[AccountEntity.balance]!!
                                val bankId=it[AccountEntity.bankId]!!
                                AccountModel(id,balance,customerId,bankId)
                            }.firstOrNull()
                            if(dacc==null){
                                call.respond(status = HttpStatusCode(400,"Not ok"), message = ResponseModel("Deposit account not found!"))
                            }
                            else{
                                wacc.balance-=req.amount
                                dacc.balance+=req.amount
                                val res=db.update(AccountEntity){
                                    set(it.balance,wacc.balance)
                                    where {
                                        it.id eq wacc.id
                                    }
                                }
                                val tran=db.insert(TransactionEntity){
                                    set(it.amount,req.amount)
                                    set(it.type,"DEBIT")
                                    set(it.accountId,wacc.id)
                                    set(it.customerId,obj.customerId)
                                    set(it.bankId,wacc.bankId)
                                }

                                val res1=db.update(AccountEntity){
                                    set(it.balance,dacc.balance)
                                    where {
                                        it.id eq dacc.id
                                    }
                                }
                                val tran1=db.insert(TransactionEntity){
                                    set(it.amount,req.amount)
                                    set(it.type,"CREDIT")
                                    set(it.accountId,dacc.id)
                                    set(it.customerId,dacc.customerId)
                                    set(it.bankId,dacc.bankId)
                                }
                                call.respond(status = HttpStatusCode.OK, message = ResponseModel("Successfully Transfered!"))

                            }

                        }
                    }


                }
            }
        }
    }
}