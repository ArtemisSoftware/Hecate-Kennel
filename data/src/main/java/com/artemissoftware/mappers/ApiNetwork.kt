package com.artemissoftware.mappers

import com.artemissoftware.domain.ApiNetworkError
import com.artemissoftware.errors.HecateKennelApiNetworkException

fun HecateKennelApiNetworkException.toApiNetworkError() = ApiNetworkError(
    code = code,
    message = message,
    errors = errors
)