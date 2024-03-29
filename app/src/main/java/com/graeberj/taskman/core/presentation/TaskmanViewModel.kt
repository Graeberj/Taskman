package com.graeberj.taskman.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskmanViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val preferences: Preferences
) : ViewModel() {

    private val _state = MutableStateFlow(TaskmanState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            when (repository.authenticateUser()) {
                is AuthResult.Authorized -> {
                    _state.update { it.copy(isLoggedIn = true, isLoading = false) }
                }

                is AuthResult.Unauthorized -> {
                    _state.update { it.copy(isLoggedIn = false, isLoading = false) }
                }

                is AuthResult.Error -> {
                    _state.update { it.copy(isLoggedIn = false, isLoading = false) }
                }
            }
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            withContext(NonCancellable) {
                preferences.deleteUser()
            }
        }
    }
}