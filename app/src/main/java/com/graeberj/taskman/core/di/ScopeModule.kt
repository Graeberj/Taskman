package com.graeberj.taskman.core.di

import com.graeberj.taskman.core.annotations.DefaultDispatcher
import com.graeberj.taskman.core.annotations.DefaultScope
import com.graeberj.taskman.core.annotations.IoDispatcher
import com.graeberj.taskman.core.annotations.IoScope
import com.graeberj.taskman.core.annotations.MainDispatcher
import com.graeberj.taskman.core.annotations.MainScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object ScopeModule {

    @DefaultScope
    @Provides
    fun providesDefaultScope(
        @DefaultDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @IoScope
    @Provides
    fun providesIoScope(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @MainScope
    @Provides
    fun providesMainScope(
        @MainDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }
}