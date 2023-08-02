package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.model.UserCredentials
import io.ktor.server.config.*
import java.util.*

class TokenManager(val config:HoconApplicationConfig) {
    val audience=config.property("audience").getString()
    val secret= config.property("secret").getString()
    val issuer = config.property("issuer").getString()
    //        val realm =  config.property("audience").getString()
    val expiryDate=System.currentTimeMillis() + 600000
    fun generateJwtToken(user:UserCredentials) : String{

        val token= JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("customerId",user.customerId)
            .withClaim("password",user.password)
            .withExpiresAt(Date(expiryDate))
            .sign(Algorithm.HMAC256(secret))
    return token;
    }

    fun verifyJwtToken():JWTVerifier{
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }

}