package com.graeberj.taskman.agenda.di

import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.data.repository.AgendaRepositoryImpl
import com.graeberj.taskman.agenda.data.repository.EventRepositoryImpl
import com.graeberj.taskman.agenda.data.repository.ReminderRepositoryImpl
import com.graeberj.taskman.agenda.data.repository.TaskRepositoryImpl
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import com.graeberj.taskman.agenda.domain.repository.EventRepository
import com.graeberj.taskman.agenda.domain.repository.ReminderRepository
import com.graeberj.taskman.agenda.domain.repository.TaskRepository
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.core.constants.Constants
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
object AgendaModule {

    @Provides
    @Singleton
    fun provideAgendaApi(okHttpClient: OkHttpClient): AgendaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AgendaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAgendaRepository(api: AgendaApi, serializer: JsonSerializer): AgendaRepository {
        return AgendaRepositoryImpl(api, serializer)
    }

    @Provides
    @Singleton
    fun provideEventRepository(
        api: AgendaApi,
        moshi: Moshi,
        serializer: JsonSerializer
    ): EventRepository {
        return EventRepositoryImpl(api, moshi, serializer)
    }

    @Provides
    @Singleton
    fun provideReminderRepository(api: AgendaApi, serializer: JsonSerializer): ReminderRepository {
        return ReminderRepositoryImpl(api, serializer)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(api: AgendaApi, serializer: JsonSerializer): TaskRepository {
        return TaskRepositoryImpl(api, serializer)
    }


}