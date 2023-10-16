package com.graeberj.taskman.auth.domain.models

data class LoggedInUser(
    val token: String = "",
    val userId: String = "",
    val fullName: String = ""
)
