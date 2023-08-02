package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountModel(val id:Int, var balance:Float, val customerId:Int, val bankId:Int)
