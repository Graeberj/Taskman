package com.graeberj.taskman.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.graeberj.taskman.agenda.presentation.home.HomeScreen
import com.graeberj.taskman.auth.presentation.login.LoginScreen
import com.graeberj.taskman.auth.presentation.login.LoginViewModel
import com.graeberj.taskman.auth.presentation.registration.RegistrationScreen
import com.graeberj.taskman.auth.presentation.registration.RegistrationViewModel


@Composable
fun TaskmanNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.AuthGroup) {
        navigation(
            startDestination = Routes.LoginScreen,
            route = Routes.AuthGroup
        ) {
            composable(Routes.LoginScreen) {
                val viewModel = hiltViewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignupClick = { navController.navigate(Routes.RegistrationScreen) },
                    onLogin = {
                        navController.navigate(Routes.AgendaGroup){
                            popUpTo(Routes.AuthGroup) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Routes.RegistrationScreen) {
                val viewModel = hiltViewModel<RegistrationViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                RegistrationScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }

        navigation(
            startDestination = Routes.HomeScreen,
            route = Routes.AgendaGroup
        ) {
            composable(Routes.HomeScreen) {
                HomeScreen()
            }
        }
    }
}