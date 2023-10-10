package com.graeberj.taskman.auth.domain.repository

import com.graeberj.taskman.auth.data.mapper.toLoginResponse
import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.domain.models.LoggedInUser

class AuthRepositoryImpl(private val api: ApiService) : AuthRepository {
    override suspend fun loginUser(email: String, password: String): Result<LoggedInUser> {
        val request = LoginRequestDto(email = email, password = password)
        val response = api.loginUser(request).toLoginResponse()
        return Result.success(response)

    }

    override suspend fun registerUser(name: String, email: String, password: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun authenticateUser(): Result<Unit> {
        TODO("Not yet implemented")
    }
}