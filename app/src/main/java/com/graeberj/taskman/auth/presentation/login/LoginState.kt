package com.graeberj.taskman.auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val isUserLoggedIn: Boolean = false,
    val errorMessage: String? = null
)