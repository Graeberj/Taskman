package com.graeberj.taskman.auth.domain.repository

import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.LoginResponseDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import retrofit2.Call

class AuthRepositoryImpl(private val api: ApiService) : AuthRepository {
    override suspend fun loginUser(loginRequest: LoginRequestDto): Call<LoginResponseDto> {
        return api.loginUser(loginRequest)
    }

    override suspend fun registerUser(registrationRequest: RegistrationRequestDto): Call<Void> {
        return api.registerUser(registrationRequest)
    }

    override suspend fun authenticateUser(token: String): Call<Void> {
        return api.checkAuthentication(token)
    }

    override suspend fun logout(token: String): Call<Void> {
        return api.logout(token)
    }
}