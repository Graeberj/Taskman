package com.graeberj.taskman.auth.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.auth.domain.usecase.ValidateFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val validateForm: ValidateFormUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            RegistrationEvent.PopBackStack -> TODO()
            RegistrationEvent.Submit -> {
                if (!validateForm.validateFullName(state.value.username)) {
                    _state.update { it.copy(showUsernameError = true) }
                }
                if (!validateForm.validateEmail(state.value.email)) {
                    _state.update { it.copy(showEmailError = true) }
                }
                if (!validateForm.validatePassword(state.value.password)) {
                    _state.update { it.copy(showPasswordError = true) }
                }
                if (!state.value.showUsernameError && state.value.showEmailError && state.value.showPasswordError) {
                    submit(state.value.username, state.value.email, state.value.password)
                }
//                if (!state.nameError && !state.emailError && !state.passwordError) {
//                    submit(state.name, state.email, state.password)
//                }
            }

            RegistrationEvent.TogglePasswordVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordHidden = !it.isPasswordHidden
                    )
                }
            }

            is RegistrationEvent.ValidateEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        isEmailValid = validateForm.validateEmail(event.email),
                        showEmailError = false
                    )
                }
            }

            is RegistrationEvent.ValidateName -> {
                _state.update {
                    it.copy(
                        username = event.name,
                        isUsernameValid = validateForm.validateFullName(event.name),
                        showUsernameError = false
                    )
                }
            }

            is RegistrationEvent.ValidatePassword -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        showEmailError = false
                    )
                }
            }
        }
    }

    private fun submit(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(fullName = username, email = email, password = password)
        }

    }
}