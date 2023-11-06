package com.graeberj.taskman.auth.domain.repository

import com.graeberj.taskman.auth.data.mapper.toLoggedInUser
import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.UiText
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall
import com.graeberj.taskman.util.Resource

class AuthRepositoryImpl(private val api: ApiService, private val serializer: JsonSerializer) :
    AuthRepository {


    override suspend fun loginUser(email: String, password: String): Resource<LoggedInUser> {
        val loginRequestDto = LoginRequestDto(email, password)

        val result = authenticatedRetrofitCall(serializer) {
            val response = api.loginUser(loginRequestDto)
            AuthResult.Authorized(response)
        }
        return when (result) {
            is AuthResult.Authorized -> {
                Resource.Success(data = result.data?.toLoggedInUser())
            }
            is AuthResult.Unauthorized -> {
                Resource.Error(message = UiText.DynamicString("Unauthorized access"))
            }
            is AuthResult.Error -> {
                Resource.Error(message = UiText.DynamicString(result.message.toString()))
            }
        }
    }

    override suspend fun registerUser(
        email: String,
        password: String,
        fullName: String
    ): Resource<Boolean> {
        val requestBody = RegistrationRequestDto(email = email, fullName = fullName, password = password)

        val result = authenticatedRetrofitCall(serializer) {
            api.registerUser(requestBody)
            AuthResult.Authorized(true)
        }
        return when (result) {
            is AuthResult.Authorized -> Resource.Success(data = true)
            is AuthResult.Unauthorized -> Resource.Error(UiText.DynamicString("Unauthorized access"))
            is AuthResult.Error -> Resource.Error(UiText.DynamicString(result.message.toString()))
        }
    }


    override suspend fun authenticateUser(): Resource<Boolean> {
        val result = authenticatedRetrofitCall(serializer) {
            api.checkAuthentication()
            AuthResult.Authorized(true) // Authentication success returns a boolean.
        }
        return when (result) {
            is AuthResult.Authorized -> Resource.Success(data = true)
            is AuthResult.Unauthorized -> Resource.Error(UiText.DynamicString("Unauthorized access"))
            is AuthResult.Error -> Resource.Error(UiText.DynamicString(result.message.toString()))
        }
    }

    override suspend fun logout(): Resource<Unit> {
        val result = authenticatedRetrofitCall(serializer) {
            api.logout()
            AuthResult.Authorized(Unit)
        }
        return when (result) {
            is AuthResult.Authorized -> Resource.Success(data = Unit)
            is AuthResult.Unauthorized -> Resource.Error(UiText.DynamicString("Unauthorized access"))
            is AuthResult.Error -> Resource.Error(UiText.DynamicString(result.message.toString()))
        }
    }

}