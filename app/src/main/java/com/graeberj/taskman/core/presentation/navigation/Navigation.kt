package com.graeberj.taskman.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.presentation.agenda.main.AgendaScreen
import com.graeberj.taskman.agenda.presentation.agenda.main.AgendaType
import com.graeberj.taskman.agenda.presentation.agenda.main.AgendaViewModel
import com.graeberj.taskman.agenda.presentation.event.EventViewModel
import com.graeberj.taskman.auth.presentation.login.LoginScreen
import com.graeberj.taskman.auth.presentation.login.LoginViewModel
import com.graeberj.taskman.auth.presentation.registration.RegistrationScreen
import com.graeberj.taskman.auth.presentation.registration.RegistrationViewModel


@Composable
fun TaskmanNavigation(navController: NavHostController, onLogout: () -> Unit) {
    NavHost(navController = navController, startDestination = Routes.AUTH_GROUP) {
        navigation(
            startDestination = Routes.LOGIN,
            route = Routes.AUTH_GROUP
        ) {
            composable(Routes.LOGIN) {
                val viewModel = hiltViewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onSignupClick = { navController.navigate(Routes.REGISTRATION) },
                    onLogin = {
                        navController.navigate(Routes.AGENDA_GROUP) {
                            popUpTo(Routes.AUTH_GROUP) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Routes.REGISTRATION) {
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
            startDestination = Routes.HOME,
            route = Routes.AGENDA_GROUP
        ) {
            composable(Routes.HOME) {
                val viewModel = hiltViewModel<AgendaViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                AgendaScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onLogout = {
                        onLogout()
                        navController.popBackStack()
                        navController.navigate(Routes.HOME)
                    },
                    redirect = { type, date ->
                        val route = when (type) {
                            is AgendaType.Event -> Routes.EVENT
                            is AgendaType.Task -> Routes.TASK
                            is AgendaType.Reminder -> Routes.REMINDER
                        }
                        navController.navigate("$route?action=create&date=$date")
                    },
                    options = { itemOptions, item ->
                        val route = when (item) {
                            is AgendaItem.Event -> Routes.EVENT
                            is AgendaItem.Task -> Routes.TASK
                            is AgendaItem.Reminder -> Routes.REMINDER
                        }
                        navController.navigate("$route?action=${itemOptions.name}&id=${item.id}")
                    }
                )
            }
            composable(
                route = Routes.EVENT + "?date={date}&action={action}&id={id}",
                arguments = listOf(
                    navArgument("date") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("action") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("id") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) {
                val eventTitle = it.savedStateHandle.get<String>("event_title") ?: ""
                val eventDescription = it.savedStateHandle.get<String>("event_description") ?: ""
                val viewModel = hiltViewModel<EventViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

            }
        }
    }
}