package com.graeberj.taskman.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.graeberj.taskman.core.presentation.navigation.TaskmanNavigation
import com.graeberj.taskman.ui.theme.TaskmanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val taskmanViewModel by viewModels<TaskmanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            taskmanViewModel.state.value.isLoading
        }
        setContent {
            TaskmanTheme {
                val navController = rememberNavController()
                TaskmanNavigation(navController = navController) {
                    taskmanViewModel.onLogout()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    TaskmanTheme {
//        LoginScreen()
    }
}