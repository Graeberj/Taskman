package com.graeberj.taskman.auth.domain.repository


import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.LoginResponseDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import retrofit2.Call

interface AuthRepository {
    suspend fun loginUser(loginRequest: LoginRequestDto): Call<LoginResponseDto>
    suspend fun registerUser(registrationRequestDto: RegistrationRequestDto): Call<Void>
    suspend fun authenticateUser(token: String): Call<Void>

    suspend fun logout(token: String): Call<Void>
}