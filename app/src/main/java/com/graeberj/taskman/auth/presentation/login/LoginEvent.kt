package com.graeberj.taskman.auth.presentation.login

sealed class LoginEvent {
    data class ValidateEmail(val email: String) : LoginEvent()
    data class ValidatePassword(val password: String) : LoginEvent()
    data object TogglePasswordVisibility : LoginEvent()
    data object Submit : LoginEvent()
}