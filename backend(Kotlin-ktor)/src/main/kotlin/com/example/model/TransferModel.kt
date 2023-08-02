package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class TransferModel(val amount:Float,val customerId:Int,val password:String,val depositAccountId:Int,val withdrawAccountId:Int)
