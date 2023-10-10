package com.graeberj.taskman.auth.domain.repository


import com.graeberj.taskman.auth.domain.models.LoggedInUser

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Result<LoggedInUser>
    suspend fun registerUser(name: String, email: String, password: String): Result<Unit>
    suspend fun authenticateUser(): Result<Unit>
}