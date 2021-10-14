package com.artemissoftware

import com.artemissoftware.errors.HecateKennelApiNetworkException
import com.artemissoftware.remote.dto.ErrorResponseDto
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try{

            callFunction.invoke()

        }
        catch (ex: Exception){

//                e.printStackTrace()
//                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when(ex){
                    is HttpException -> {

                        convertErrorBody(ex)?.let { error ->
                            throw HecateKennelApiNetworkException(
                                code = error.code,
                                message = error.message,
                                errors = listOf(error.level)
                            )
                        } ?: run {
                            throw ex
                        }

                    }
                    is SocketTimeoutException -> throw ex//emitter.onError(ErrorType.TIMEOUT)
                    is IOException -> throw ex//emitter.onError(ErrorType.NETWORK)
                    else -> throw ex//emitter.onError(ErrorType.UNKNOWN)
                }
        }
    }


    private fun convertErrorBody(httpException: HttpException): ErrorResponseDto? {
        return try {
            httpException.response()?.errorBody()?.let {
                Gson().fromJson(it.charStream(), ErrorResponseDto::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }


}