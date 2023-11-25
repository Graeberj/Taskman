package com.graeberj.taskman.auth.data.remote.api

import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.LoginResponseDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/register")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequestDto): Response<Unit>

    @POST("/login")
    suspend fun loginUser(@Body body: LoginRequestDto): LoginResponseDto

    @GET("/authenticate")
    suspend fun checkAuthentication()

    @GET("/logout")
    suspend fun logout(): ResponseBody


}