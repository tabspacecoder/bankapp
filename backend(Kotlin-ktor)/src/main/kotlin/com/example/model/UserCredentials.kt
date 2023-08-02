package com.example.model

import com.sun.org.apache.xpath.internal.operations.Bool
import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class UserCredentials (val customerId:Int, val password:String){
    fun hashedPassword():String{
        return BCrypt.hashpw(password,BCrypt.gensalt())
    }
    fun isValidCredentials():Boolean{
        return password.length>=6
    }
}