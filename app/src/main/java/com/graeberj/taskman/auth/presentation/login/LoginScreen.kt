package com.graeberj.taskman.auth.presentation.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.R
import com.graeberj.taskman.auth.presentation.components.EmailTextField
import com.graeberj.taskman.auth.presentation.components.PasswordTextField
import com.graeberj.taskman.auth.presentation.components.TaskmanButton
import com.graeberj.taskman.auth.presentation.components.TopGreeting

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignupClick: () -> Unit,
    onLogin: () -> Unit
) {
    val isLoggedIn = state.isUserLoggedIn
    LaunchedEffect(key1 = isLoggedIn){
        if(isLoggedIn) {
            onLogin()
        }
    }
    TopGreeting(
        greetingResId = R.string.welcome_back
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            EmailTextField(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { onEvent(LoginEvent.ValidateEmail(it))},
                placeholder = stringResource(R.string.email_address),
                isValid = state.isEmailValid,
                errorMessage = if (state.isPasswordValid) state.errorMessage else null
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = {onEvent(LoginEvent.ValidatePassword(it))},
                onIconClicked = {onEvent(LoginEvent.TogglePasswordVisibility)},
                placeholder = stringResource(R.string.password),
                isHidden = state.isPasswordHidden,
                isValid = state.isPasswordValid,
                errorMessage = if (state.isPasswordValid) state.errorMessage else null


            )
            Spacer(modifier = Modifier.height(25.dp))
            TaskmanButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(R.string.log_in),
                onClick = {onEvent(LoginEvent.Submit)}
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = { onSignupClick() }) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                ) {
                                    append(stringResource(R.string.don_t_have_an_account))
                                    append(" ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 14.sp
                                    )
                                ) {
                                    append(stringResource(R.string.sign_up))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewLoginScreen() {
//    LoginScreen()
}