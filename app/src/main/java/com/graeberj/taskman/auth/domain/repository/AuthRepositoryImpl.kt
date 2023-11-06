package com.graeberj.taskman.auth.domain.repository

import com.graeberj.taskman.auth.data.mapper.toLoggedInUser
import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.util.Resource
import kotlin.coroutines.cancellation.CancellationException

class AuthRepositoryImpl(private val api: ApiService) : AuthRepository {
    override suspend fun loginUser(email: String, password: String): Resource<LoggedInUser> {
        val loginRequestDto = LoginRequestDto(email, password)
        return try {
            val result = api.loginUser(loginRequestDto)
            Resource.Success(data = result.toLoggedInUser())
        } catch (e: Exception) {
            if (e == CancellationException()){
                throw e
            } else {
                Resource.Error(message = e.toString())
                //once I'm more comfortable with error handling, I'll implement some sort of custom
                // error handling
            }
        }
    }

    override suspend fun registerUser(
        email: String,
        password: String,
        fullName: String
    ): Resource<Boolean> {
        val requestBody =
            RegistrationRequestDto(email = email, fullName = fullName, password = password)
        return try {
            api.registerUser(requestBody)
            Resource.Success(data = true)
        } catch (e: Exception) {
            if (e == CancellationException()){
                throw e
            } else {
                Resource.Error(message = e.toString())
            }
        }
    }

    override suspend fun authenticateUser(): Resource<Boolean> {
        return try {
            api.checkAuthentication()
            Resource.Success(data = true)
        } catch (e: Exception) {
            if (e == CancellationException()){
                throw e
            } else {
                Resource.Error(message = e.toString())
            }
        }
    }

    override suspend fun logout(): Resource<Unit> {
        return try {
            api.logout()
            Resource.Success(data = Unit)
        } catch (e: Exception) {
            if (e == CancellationException()){
                throw e
            } else {
                Resource.Error(message = e.toString())
            }
        }
    }
}