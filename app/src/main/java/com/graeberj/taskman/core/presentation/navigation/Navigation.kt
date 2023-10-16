package com.graeberj.taskman.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.graeberj.taskman.auth.presentation.login.LoginScreen
import com.graeberj.taskman.core.presentation.TaskmanSplashScreen


@Composable
fun TaskmanNavigation (window: android.view.Window){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Routes.TaskmanSplashScreen.name){
        composable(Routes.TaskmanSplashScreen.name){
            TaskmanSplashScreen(navController = navController, window = window)
        }
        composable(Routes.LoginScreen.name){
            LoginScreen(onSignupClick = {navController.navigate(Routes.RegistrationScreen.name)})
        }
    }

}
