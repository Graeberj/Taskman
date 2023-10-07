package com.graeberj.taskman.auth.presentation.registration

data class RegistrationState(
    val error: String? = null,
    val loading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val password: String = "",

)