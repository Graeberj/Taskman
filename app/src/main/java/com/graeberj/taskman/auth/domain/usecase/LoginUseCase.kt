package com.graeberj.taskman.auth.domain.usecase


import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.auth.domain.repository.AuthRepository
class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<LoggedInUser> {
        return authRepository.loginUser(email, password)
    }
}