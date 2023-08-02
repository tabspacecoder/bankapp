package com.example.model

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class RegisterModel(val password:String,val name:String,val email:String,val bankId:Int){
    fun hashedPassword():String{
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

}
