package com.artemissoftware.errors

import java.lang.RuntimeException

data class HecateKennelApiNetworkException(
    val code: Int?,
    override val message: String?,
    val errors: List<String>?
): RuntimeException()