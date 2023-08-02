package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerModel(val id:Int,val password:String,val name:String,val email:String,val bankId:Int)
