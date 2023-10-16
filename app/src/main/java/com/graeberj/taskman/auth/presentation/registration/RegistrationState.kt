package com.graeberj.taskman.auth.presentation.registration

data class RegistrationState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val showUsernameError: Boolean = false,
    val showEmailError: Boolean = false,
    val showPasswordError: Boolean = false,
    val isEmailValid: Boolean = false,
    val isUsernameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPasswordHidden: Boolean = true
)