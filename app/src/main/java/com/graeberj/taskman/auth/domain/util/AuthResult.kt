package com.graeberj.taskman.auth.domain.util

sealed class AuthResult<out T>(val data: T? = null, val message: UiText? = null) {
    class Authorized<T>(data: T): AuthResult<T>(data)
    class Unauthorized<T>(message: UiText? = null): AuthResult<T>(message = message)
    class Error<T>(message: UiText): AuthResult<T>(message = message)
}
