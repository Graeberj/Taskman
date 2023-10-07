package com.graeberj.taskman.auth.data.model

import com.squareup.moshi.Json

data class LoginResponse(
    @field:Json(name = "fullName")
    val fullName: String,
    @field:Json(name = "token")
    val token: String,
    @field:Json(name = "userId")
    val userId: String
)