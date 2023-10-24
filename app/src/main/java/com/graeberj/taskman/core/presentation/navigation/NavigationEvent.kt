package com.graeberj.taskman.core.presentation.navigation

sealed class NavigationEvent{
    data object NavigateToHome : NavigationEvent()
}
