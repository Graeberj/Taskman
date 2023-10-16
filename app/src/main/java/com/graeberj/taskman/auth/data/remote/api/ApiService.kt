package com.graeberj.taskman.auth.data.remote.api

import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.LoginResponseDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    companion object {
        const val BASE_URL = "https://tasky.pl-coding.com/"
    }

    @POST("/register")
    fun registerUser(@Body registrationRequest: RegistrationRequestDto): Call<Void>

    @POST("/login")
    fun loginUser(@Body body: LoginRequestDto): Call<LoginResponseDto>

    @GET("/authenticate")
    suspend fun checkAuthentication(@Header("Authorization") token: String): Call<Void>

    @GET("/logout")
    fun logout(@Header("Authorization") token: String): Call<Void>


}