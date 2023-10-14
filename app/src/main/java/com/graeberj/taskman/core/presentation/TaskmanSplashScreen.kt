package com.graeberj.taskman.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.graeberj.taskman.R
import com.graeberj.taskman.core.presentation.navigation.Routes
import com.graeberj.taskman.ui.theme.TaskyGreen
import kotlinx.coroutines.delay

@Composable
fun TaskmanSplashScreen(navController: NavController, window: android.view.Window) {

    WindowCompat.setDecorFitsSystemWindows(window, false)


    LaunchedEffect(key1 = true) {

        delay(2000L)
        navController.navigate(Routes.LoginScreen.name)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TaskyGreen),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_tasky_logo),
            "Tasky logo",
            tint = Color.Unspecified
        )
    }

}

