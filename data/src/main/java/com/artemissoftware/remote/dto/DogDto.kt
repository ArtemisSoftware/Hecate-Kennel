package com.artemissoftware.remote.dto


import com.google.gson.annotations.SerializedName

data class DogDto(
    @SerializedName("breeds")
    val breeds: List<BreedDto>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)