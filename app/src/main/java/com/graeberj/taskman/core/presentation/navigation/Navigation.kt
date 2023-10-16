package com.graeberj.taskman.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.graeberj.taskman.auth.presentation.login.LoginScreen
import com.graeberj.taskman.auth.presentation.login.LoginViewModel
import com.graeberj.taskman.auth.presentation.registration.RegistrationScreen
import com.graeberj.taskman.auth.presentation.registration.RegistrationViewModel


@Composable
fun TaskmanNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LoginScreen.name) {

        composable(Routes.LoginScreen.name) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsState()
            LoginScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onSignupClick = { navController.navigate(Routes.RegistrationScreen.name) }
            )
        }
        composable(Routes.RegistrationScreen.name) {
            val viewModel = hiltViewModel<RegistrationViewModel>()
            val state by viewModel.state.collectAsState()
            RegistrationScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }

}
