package com.graeberj.taskman.auth.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "password")
    val password: String
)
