package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountModel(val balance:Float,val customerId:Int,val bankId:Int,val password:String)
