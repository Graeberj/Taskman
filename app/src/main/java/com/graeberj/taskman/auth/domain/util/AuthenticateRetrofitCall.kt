package com.graeberj.taskman.auth.domain.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


suspend inline fun <T> authenticatedRetrofitCall(
    jsonSerializer: JsonSerializer,
    crossinline doCall: suspend () -> AuthResult<T>
): AuthResult<T> {
    return try {
        doCall()
    } catch(e: HttpException) {
        e.printStackTrace()
        e.asAuthResult(jsonSerializer)
    } catch(e: IOException) {
        e.printStackTrace()
        AuthResult.Error(UiText.UnknownError)
    }
}

suspend fun HttpException.getErrorMessage(serializer: JsonSerializer): ErrorMessage? {
    return withContext(Dispatchers.IO) {
        response()?.errorBody()?.bytes()?.decodeToString()?.let { body ->
            serializer.fromJson(body, ErrorMessage::class.java)
        }
    }
}

fun HttpException.isUnauthorized(): Boolean {
    return code() == 401
}

suspend fun <T> HttpException.asAuthResult(serializer: JsonSerializer): AuthResult<T> {
    if(isUnauthorized()) {
        return AuthResult.Unauthorized()
    }
    val message = getErrorMessage(serializer)?.message
    return AuthResult.Error(
        message = if(message != null) {
            UiText.DynamicString(message)
        } else UiText.UnknownError
    )
}