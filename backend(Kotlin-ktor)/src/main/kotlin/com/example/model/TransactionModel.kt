package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class TransactionModel(val id:Int,val type:String,val amount:Float,val accountId:Int,val bankId:Int,val customerId:Int)
