package com.graeberj.taskman.core.presentation.navigation

enum class Routes {
    TaskmanSplashScreen,
    LoginScreen,
    RegistrationScreen;

    companion object {
        fun fromRoute(route: String?): Routes =
            when (route?.substringBefore(delimiter = "/")) {
                TaskmanSplashScreen.name -> TaskmanSplashScreen
                LoginScreen.name -> LoginScreen
                RegistrationScreen.name -> RegistrationScreen
                else -> throw IllegalArgumentException("Route $route does not exist!")
            }

    }
}