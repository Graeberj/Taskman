package com.graeberj.taskman.auth.presentation.registration

data class RegistrationState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val usernameError: Boolean = false,
    val showEmailError: Boolean = false,
    val passwordError: Boolean = false,
    val isEmailValid: Boolean = false,
    val isUsernameValid: Boolean = false,
    val isPasswordHidden: Boolean = true
)