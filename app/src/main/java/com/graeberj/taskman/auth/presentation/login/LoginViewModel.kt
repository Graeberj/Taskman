package com.graeberj.taskman.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Submit -> TODO()
            LoginEvent.TogglePasswordVisibility -> TODO()
            is LoginEvent.ValidateEmail -> TODO()
            is LoginEvent.ValidatePassword -> TODO()
        }

    }
}