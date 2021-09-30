package com.artemissoftware.remote.dto


import com.google.gson.annotations.SerializedName

data class HeightDto(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String
)