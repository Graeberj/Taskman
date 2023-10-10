package com.graeberj.taskman.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
}