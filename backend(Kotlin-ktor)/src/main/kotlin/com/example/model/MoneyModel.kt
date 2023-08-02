package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class MoneyModel(val customerId:Int,val password: String,val accountId:Int,val amount:Float)
