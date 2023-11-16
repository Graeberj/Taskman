package com.graeberj.taskman.auth.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.auth.domain.usecase.ValidateFormUseCase
import com.graeberj.taskman.core.presentation.navigation.NavigationEvent
import com.graeberj.taskman.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val eventChannel = Channel<NavigationEvent>()
    val navigationEvent = eventChannel.receiveAsFlow()
    // working with chat gpt and this was suggested, I'm going to look into this deeper

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            RegistrationEvent.Submit -> {
                val stateVal = state.value
                if (!validateForm.validateFullName(stateVal.username)) {
                    _state.update { it.copy(errorMessage = "Username Invalid") }
                }
                if (!validateForm.validateEmail(stateVal.email)) {
                    _state.update { it.copy(errorMessage = "Email Invalid") }
                }
                if (!validateForm.validatePassword(stateVal.password)) {
                    _state.update { it.copy(errorMessage = "Password Invalid") }
                }
                if (stateVal.isUsernameValid && stateVal.isEmailValid && stateVal.isPasswordValid) {
                    submit(stateVal.username, stateVal.email, stateVal.password)
                }
            }

            RegistrationEvent.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordHidden = !it.isPasswordHidden) }
            }

            is RegistrationEvent.ValidateEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        isEmailValid = validateForm.validateEmail(event.email),
                    )
                }
            }

            is RegistrationEvent.ValidateName -> {
                _state.update {
                    it.copy(
                        username = event.name,
                        isUsernameValid = validateForm.validateFullName(event.name),
                    )
                }
            }

            is RegistrationEvent.ValidatePassword -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        isPasswordValid = validateForm.validatePassword(event.password)
                    )
                }
            }
        }
    }

    private fun submit(username: String, email: String, password: String) {
        viewModelScope.launch {
            when (val result =
                repository.registerUser(fullName = username, email = email, password = password)) {
                is Resource.Success -> {
                    if (result.data == true) {
                        eventChannel.send(NavigationEvent.NavigateToHome)
                    } else {
                        _state.update { it.copy(errorMessage = "Registration failed. Please try again.") }
                    }
                }

                is Resource.Error -> {
                    _state.update { it.copy(errorMessage = result.message.toString()) }
                }
            }
        }
    }
}