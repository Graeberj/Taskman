package com.graeberj.taskman.auth.di.modules

import com.graeberj.taskman.auth.data.remote.api.ApiService
import com.graeberj.taskman.auth.domain.repository.AuthRepository
import com.graeberj.taskman.auth.domain.repository.AuthRepositoryImpl
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.MoshiJsonSerializer
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule{

    @Provides
    @Singleton
    fun provideAuthRepository(api: ApiService, serializer: JsonSerializer): AuthRepository {
        return AuthRepositoryImpl(api, serializer)
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