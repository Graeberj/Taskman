package com.graeberj.taskman.util

import com.graeberj.taskman.auth.domain.util.UiText

sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(message: UiText) : Resource<T>(message = message)
}

