package com.artemissoftware.domain

data class ApiNetworkError(
    val code: Int? = null,
    val message: String? = null,
    val errors: List<String>? = null
)