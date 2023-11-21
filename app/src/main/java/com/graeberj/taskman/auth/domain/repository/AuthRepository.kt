package com.graeberj.taskman.auth.domain.repository


import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.auth.domain.util.AuthResult

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): AuthResult<LoggedInUser>
    suspend fun registerUser(email: String, password: String, fullName: String): AuthResult<Unit>
    suspend fun authenticateUser(): AuthResult<Unit>
    suspend fun logout(): AuthResult<Unit>
}