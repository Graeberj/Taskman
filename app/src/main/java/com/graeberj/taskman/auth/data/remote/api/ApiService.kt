package com.graeberj.taskman.auth.data.remote.api

import com.graeberj.taskman.auth.data.model.LoginRequest
import com.graeberj.taskman.auth.data.model.LoginResponse
import com.graeberj.taskman.auth.data.model.RegistrationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    companion object {
        const val BASE_URL = "https://tasky.pl-coding.com/"
    }

    @POST("/register")
    fun registerUser(@Body registrationRequest: RegistrationRequest)

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/authenticate")
    suspend fun checkAuthentication()


}