package com.graeberj.taskman.auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val showEmailError: Boolean = false,
    val isEmailValid: Boolean = false,
    val showPasswordError: Boolean = false,
    val isPasswordHidden: Boolean = true,
    val isUserLoggedIn: Boolean = false
)