package com.graeberj.taskman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.graeberj.taskman.core.presentation.navigation.TaskmanNavigation
import com.graeberj.taskman.ui.theme.TaskmanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TaskmanTheme {
                TaskmanNavigation()
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