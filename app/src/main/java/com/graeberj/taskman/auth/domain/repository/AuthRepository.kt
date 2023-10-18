package com.graeberj.taskman.auth.domain.repository


import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.util.Resource

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Resource<LoggedInUser>
    suspend fun registerUser(email: String, password: String, fullName: String): Resource<Boolean>
    suspend fun authenticateUser(): Resource<Boolean>

    suspend fun logout(): Resource<Unit>
}