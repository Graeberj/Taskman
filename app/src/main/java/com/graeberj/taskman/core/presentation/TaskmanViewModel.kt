package com.graeberj.taskman.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.core.domain.preferences.Preferences
import com.graeberj.taskman.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskmanViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val preferences: Preferences,
) : ViewModel() {

    private val _state = MutableStateFlow(TaskmanState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = repository.authenticateUser()){
                is Resource.Success -> {
                    val data = result.data
                    _state.update { it.copy(isLoggedIn = true, isLoading = false) }
                }
                is Resource.Error -> {
                    val errorMessage = result.message
                    _state.update { it.copy(isLoggedIn = false, isLoading = false) }
                }
            }
        }
    }
}