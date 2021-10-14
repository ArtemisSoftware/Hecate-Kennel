package com.artemissoftware

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
//                        if(e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
//                        else {
//                            val body = e.response()?.errorBody()
//                            emitter.onError(getErrorMessage(body))
//                        }

                        throw ex
                    }
                    is SocketTimeoutException -> throw ex//emitter.onError(ErrorType.TIMEOUT)
                    is IOException -> throw ex//emitter.onError(ErrorType.NETWORK)
                    else -> throw ex//emitter.onError(ErrorType.UNKNOWN)
                }
        }
    }
}