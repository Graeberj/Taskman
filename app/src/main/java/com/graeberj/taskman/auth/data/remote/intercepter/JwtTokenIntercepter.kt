package com.graeberj.taskman.auth.data.remote.intercepter

import com.graeberj.taskman.core.domain.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.Response

class JwtTokenInterceptor(
    private val preferences: Preferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.loadUser()?.token
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}