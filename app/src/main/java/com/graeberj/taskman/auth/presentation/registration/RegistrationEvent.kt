package com.graeberj.taskman.auth.presentation.registration

sealed class RegistrationEvent {
    data class ValidateName(val name: String) : RegistrationEvent()
    data class ValidateEmail(val email: String) : RegistrationEvent()
    data class ValidatePassword(val password: String) : RegistrationEvent()
    data object TogglePasswordVisibility : RegistrationEvent()
    data object Submit : RegistrationEvent()
    data object PopBackStack : RegistrationEvent()
}