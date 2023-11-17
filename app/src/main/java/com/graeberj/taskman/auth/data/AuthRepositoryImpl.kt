package com.graeberj.taskman.auth.data

import com.graeberj.taskman.auth.data.mapper.toLoggedInUser
import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall

class AuthRepositoryImpl(private val api: ApiService, private val serializer: JsonSerializer) :
    AuthRepository {


    override suspend fun loginUser(email: String, password: String): AuthResult<LoggedInUser> {
        val loginRequestDto = LoginRequestDto(email, password)

        val result = authenticatedRetrofitCall(serializer) {
            val response = api.loginUser(loginRequestDto)
            AuthResult.Authorized(response.toLoggedInUser())
        }
        return result
    }

    override suspend fun registerUser(
        email: String,
        password: String,
        fullName: String
    ): AuthResult<Unit> {
        val requestBody = RegistrationRequestDto(email = email, fullName = fullName, password = password)

        val result = authenticatedRetrofitCall(serializer) {
            api.registerUser(requestBody)
            AuthResult.Authorized(Unit)
        }
        if (result is AuthResult.Authorized) {
            loginUser(email, password)
        }
        return result
    }



    override suspend fun authenticateUser(): AuthResult<Unit> {
        val result = authenticatedRetrofitCall(serializer) {
            api.checkAuthentication()
            AuthResult.Authorized(Unit)
        }
        return result
    }

    override suspend fun logout(): AuthResult<Unit> {
        val result = authenticatedRetrofitCall(serializer) {
            api.logout()
            AuthResult.Authorized(Unit)
        }
        return result
    }

}