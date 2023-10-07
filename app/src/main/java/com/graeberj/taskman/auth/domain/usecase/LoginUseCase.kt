package com.graeberj.taskman.auth.domain.usecase

import com.graeberj.taskman.auth.data.model.LoginResponse
import com.graeberj.taskman.auth.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<LoginResponse> {
        return authRepository.loginUser(email, password)
    }
}