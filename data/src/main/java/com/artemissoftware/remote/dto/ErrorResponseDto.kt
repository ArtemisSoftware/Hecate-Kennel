package com.artemissoftware.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(

    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val code: Int,
    @SerializedName("level")
    val level: String,

)
