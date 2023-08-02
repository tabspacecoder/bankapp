package com.example.routes
import com.example.db.DatabaseConnection
import com.example.entity.AccountEntity
import com.example.entity.BankEntity
import com.example.entity.CustomerEntity
import com.example.entity.TransactionEntity
import com.example.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*
import java.util.*

fun Application.customerRoutes(){
val db=DatabaseConnection.database
    routing {
    post("/api/customer/create-user") {
        val user=call.receive<RegisterModel>()
        val res=db.insert(CustomerEntity){
            set(it.password,user.password)
            set(it.email,user.email)
            set(it.name,user.name)
            set(it.bankId,user.bankId)
        }

        val ret=db.from(CustomerEntity).select().where {
            CustomerEntity.password eq user.password
            CustomerEntity.email eq user.email
            CustomerEntity.name eq user.name
            CustomerEntity.bankId eq user.bankId
        }.map {
            val id=it[CustomerEntity.id]!!
            val password=it[CustomerEntity.password]!!
            val email=it[CustomerEntity.email]!!
            val name=it[CustomerEntity.name]!!
            val bankId=it[CustomerEntity.bankId]!!
            CustomerModel(id = id, password = password,name=name,email=email,bankId=bankId)
        }.last()

        call.respond(message = ret?:"Error", status = HttpStatusCode(200,"ok"))
    }
    post("/api/customer/login") {
        val user=call.receive<UserCredentials>()
        val res=db.from(CustomerEntity).select().where {
            CustomerEntity.id eq user.customerId
            CustomerEntity.password eq user.password
        }.map {
            val customerId=it[CustomerEntity.id]!!
            val password=it[CustomerEntity.password]!!
            UserCredentials(customerId=customerId,password=password)
        }.last()
        if(res==null){
            call.respond(status = HttpStatusCode.NotFound, message = ResponseModel(message = "Invalid customer id"))
        }
        else if(res.password!=user.password){
            call.respond(status = HttpStatusCode.NotAcceptable, message = ResponseModel(message = "Passwords are mismatching"))
        }
        else{
            call.respond(status = HttpStatusCode(200,"ok"), message = ResponseModel(message = "Login Successful!"))
        }
    }
        post("/api/customer/fetch-all-details"){
            val user=call.receive<UserCredentials>()
            val res=db.from(CustomerEntity).select().where {
                CustomerEntity.id eq user.customerId
                CustomerEntity.password eq user.password
            }.map {
                val customerId=it[CustomerEntity.id]!!
                val password=it[CustomerEntity.password]!!
                val bankId=it[CustomerEntity.bankId]!!
                val email=it[CustomerEntity.email]!!
                val name=it[CustomerEntity.name]!!
                CustomerModel(id=customerId,password=password,bankId=bankId, email = email, name = name)
            }.firstOrNull()
            if(res==null){
                call.respond(status = HttpStatusCode.NotFound, message = ResponseModel(message = "Invalid customer id"))
            }
            else if(res.password!=user.password){
                call.respond(status = HttpStatusCode.NotAcceptable, message = ResponseModel(message = "Passwords are mismatching"))
            }
            else{
                val bank=db.from(BankEntity).select().where {
                    BankEntity.id eq res.bankId
                }.map {
                    val id=it[BankEntity.id]!!
                    val name=it[BankEntity.name]!!
                    BankModel(id=id,name=name)
                }.firstOrNull()!!

                val accounts=db.from(AccountEntity).select().where{
                    AccountEntity.customerId eq user.customerId
                }.map {
                    val id=it[AccountEntity.id]!!
                    val balance=it[AccountEntity.balance]!!
                    val customerId=it[AccountEntity.customerId]!!
                    val bankId=it[AccountEntity.bankId]!!
                    AccountModel(id,balance,customerId,bankId)
                }!!
                val transactions=db.from(TransactionEntity).select().where{
                    TransactionEntity.customerId eq user.customerId
                }.map {
                    val id=it[TransactionEntity.id]!!
                    val amount=it[TransactionEntity.amount]!!
                    val customerId =it[TransactionEntity.customerId]!!
                    val bankId=it[TransactionEntity.bankId]!!
                    val accountId=it[TransactionEntity.accountId]!!
                    val type=it[TransactionEntity.type]!!
                    TransactionModel(id,type,amount,accountId,bankId,customerId)
                }!!
                val ret=FetchAllReqModel(bank=bank, customer = res,accounts=accounts,transactions=transactions)
                call.respond(status = HttpStatusCode.OK, message = ret)
            }

        }
    }
}