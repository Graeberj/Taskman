package com.graeberj.taskman.auth.data.remote.api

import com.graeberj.taskman.auth.data.remote.dto.LoginRequestDto
import com.graeberj.taskman.auth.data.remote.dto.LoginResponseDto
import com.graeberj.taskman.auth.data.remote.dto.RegistrationRequestDto
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    companion object {
        const val BASE_URL = "https://tasky.pl-coding.com/"
    }

    @POST("/register")
    fun registerUser(@Body registrationRequest: RegistrationRequestDto): ResponseBody

    @POST("/login")
    fun loginUser(@Body body: LoginRequestDto): LoginResponseDto

    @GET("/authenticate")
    suspend fun checkAuthentication()

    @GET("/logout")
    fun logout(): ResponseBody


}