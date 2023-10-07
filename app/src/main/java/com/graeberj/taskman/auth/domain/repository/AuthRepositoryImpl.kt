package com.graeberj.taskman.auth.domain.repository

import com.graeberj.taskman.auth.data.model.LoginResponse
import com.graeberj.taskman.auth.data.remote.api.ApiService

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {
    override suspend fun loginUser(email: String, password: String): Result<LoginResponse> {
        TODO("Not yet implemented")
    }
}