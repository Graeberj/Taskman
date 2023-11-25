package com.graeberj.taskman.auth.di.modules

import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.data.remote.intercepter.ApiKeyInterceptor
import com.graeberj.taskman.auth.data.remote.intercepter.JwtTokenInterceptor
import com.graeberj.taskman.auth.data.util.EmailMatcherImpl
import com.graeberj.taskman.auth.domain.usecase.ValidateEmailUseCase
import com.graeberj.taskman.auth.domain.usecase.ValidateFormUseCase
import com.graeberj.taskman.auth.domain.usecase.ValidateFullNameUseCase
import com.graeberj.taskman.auth.domain.usecase.ValidatePasswordUseCase
import com.graeberj.taskman.auth.domain.util.EmailMatcher
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.MoshiJsonSerializer
import com.graeberj.taskman.core.constants.Constants.BASE_URL
import com.graeberj.taskman.core.domain.preferences.Preferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthApi(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOKhttpClient(preferences: Preferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(JwtTokenInterceptor(preferences))
            .build()
    }

    @Provides
    @Singleton
    fun provideValidateForm(validateEmail: ValidateEmailUseCase): ValidateFormUseCase {
        return ValidateFormUseCase(
            validateEmail,
            ValidatePasswordUseCase(),
            ValidateFullNameUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideEmailValidator(emailMatcher: EmailMatcher): ValidateEmailUseCase {
        return ValidateEmailUseCase(emailMatcher)
    }
    @Provides
    @Singleton
    fun provideJsonSerializer(moshi: Moshi): JsonSerializer {
        return MoshiJsonSerializer(moshi)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}