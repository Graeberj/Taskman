package com.graeberj.taskman.auth.data.remote.dto

import com.squareup.moshi.Json

data class LoginRequestDto(
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "password")
    val password: String
)
