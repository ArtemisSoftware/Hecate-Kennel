package com.artemissoftware.domain

data class ApiNetworkResponse<T>(
    val data: T? = null,
    val error: ApiNetworkError = ApiNetworkError()
)