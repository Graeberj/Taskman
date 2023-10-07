package com.graeberj.taskman.auth.data.model

import com.squareup.moshi.Json

data class RegistrationRequest(
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "fullName")
    val fullName: String,
    @field:Json(name = "password")
    val password: String
)
