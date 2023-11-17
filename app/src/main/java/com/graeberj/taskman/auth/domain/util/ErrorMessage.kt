package com.graeberj.taskman.auth.domain.util

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorMessage(val message: String)
