package com.graeberj.taskman.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.auth.domain.usecase.ValidateFormUseCase
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val validateForm: ValidateFormUseCase,
    private val preferences: Preferences
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Submit -> submit()
            LoginEvent.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordHidden = !it.isPasswordHidden) }
            }

            is LoginEvent.ValidateEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        isEmailValid = validateForm.validateEmail(event.email)
                    )
                }
            }

            is LoginEvent.ValidatePassword -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        isPasswordValid = validateForm.validatePassword(event.password)
                    )
                }
            }
        }

    }

    private fun submit() {
        val currentState = _state.value
        _state.update { it.copy(isLoading = true) }
        if (currentState.isEmailValid && currentState.isPasswordValid) {
            viewModelScope.launch {
                when (val result =
                    repository.loginUser(currentState.email, currentState.password)) {
                    is AuthResult.Authorized -> {
                        result.data?.let { user ->
                            preferences.saveToken(user.token)
                            preferences.saveFullName(user.fullName)
                            preferences.saveUserId(user.userId)
                        }
                        _state.update { it.copy(isUserLoggedIn = true, isLoading = false) }
                    }
                    is AuthResult.Unauthorized -> {
                        TODO()
                    }
                    is AuthResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = result.message.toString(),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}