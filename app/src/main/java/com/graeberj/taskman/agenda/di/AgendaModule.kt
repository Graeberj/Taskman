package com.graeberj.taskman.agenda.di

import com.graeberj.taskman.agenda.data.AgendaRepositoryImpl
import com.graeberj.taskman.agenda.data.EventRepositoryImpl
import com.graeberj.taskman.agenda.data.ReminderRepositoryImpl
import com.graeberj.taskman.agenda.data.TaskRepositoryImpl
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import com.graeberj.taskman.agenda.domain.repository.EventRepository
import com.graeberj.taskman.agenda.domain.repository.ReminderRepository
import com.graeberj.taskman.agenda.domain.repository.TaskRepository
import com.graeberj.taskman.agenda.domain.usecase.AgendaUseCases
import com.graeberj.taskman.agenda.domain.usecase.event.DeleteEvent
import com.graeberj.taskman.agenda.domain.usecase.reminder.DeleteReminder
import com.graeberj.taskman.agenda.domain.usecase.task.ChangeTaskStatus
import com.graeberj.taskman.agenda.domain.usecase.task.DeleteTask
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

    @Provides
    @Singleton
    fun provideAgendaUseCases(
        deleteTask: DeleteTask,
        deleteReminder: DeleteReminder,
        changeTaskStatus: ChangeTaskStatus,
        deleteEvent: DeleteEvent
    ): AgendaUseCases {
        return AgendaUseCases(
            deleteReminder = deleteReminder,
            deleteTask = deleteTask,
            deleteEvent = deleteEvent,
            changeTaskStatus = changeTaskStatus
        )
    }

    @Provides
    @Singleton
    fun provideDeleteReminderUseCase(
        repository: ReminderRepository
    ): DeleteReminder {
        return DeleteReminder(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(
        repository: TaskRepository
    ): DeleteTask {
        return DeleteTask(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteEventUseCase(
        repository: EventRepository
    ): DeleteEvent {
        return DeleteEvent(repository)
    }

    @Provides
    @Singleton
    fun provideChangeTaskStatusUseCase(
        repository: TaskRepository
    ): ChangeTaskStatus {
        return ChangeTaskStatus(repository)
    }
}