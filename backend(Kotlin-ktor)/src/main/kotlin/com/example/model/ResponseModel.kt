package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel<T>
    (val message:T)
