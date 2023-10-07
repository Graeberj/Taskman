package com.graeberj.taskman.auth.domain.repository

import com.graeberj.taskman.auth.data.model.LoginResponse

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Result<LoginResponse>
}