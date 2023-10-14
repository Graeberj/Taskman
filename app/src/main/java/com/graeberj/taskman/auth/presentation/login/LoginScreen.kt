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
import androidx.hilt.navigation.compose.hiltViewModel
import com.graeberj.taskman.R
import com.graeberj.taskman.auth.presentation.components.CustomButton
import com.graeberj.taskman.auth.presentation.components.EmailTextField
import com.graeberj.taskman.auth.presentation.components.PasswordTextField
import com.graeberj.taskman.auth.presentation.components.TopGreeting

@Composable
fun LoginScreen(
    onSignupClick: ()-> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state = viewModel.state


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
                onValueChange = {},
                placeholder = stringResource(R.string.email_address),
                shouldShowError = state.showEmailError,
                isValid = state.isEmailValid
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = {},
                onIconClicked = {},
                placeholder = stringResource(R.string.password),
                shouldShowError = state.showPasswordError,
                isHidden = state.isPasswordHidden

            )
            Spacer(modifier = Modifier.height(25.dp))
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(R.string.log_in),
                onClick = {}
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
                    TextButton(onClick = onSignupClick) {
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
    LoginScreen(onSignupClick = {})
}