package com.graeberj.taskman.core.presentation

data class TaskmanState(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean = false
)