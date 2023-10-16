package com.graeberj.taskman.auth.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.graeberj.taskman.R
import com.graeberj.taskman.auth.presentation.components.CustomButton
import com.graeberj.taskman.auth.presentation.components.CustomTextField
import com.graeberj.taskman.auth.presentation.components.EmailTextField
import com.graeberj.taskman.auth.presentation.components.PasswordTextField
import com.graeberj.taskman.auth.presentation.components.TopGreeting

@Composable
fun RegistrationScreen(
    onBackPress: () -> Unit
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state = viewModel.state
    TopGreeting(
        greetingResId = R.string.create_your_account
    ) {
        Column {
            CustomTextField(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                value = state.username,
                onValueChange = {},
                placeholder = "Name",
                shouldShowError = state.usernameError,
                isValid = state.isUsernameValid

            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = {},
                placeholder = "Email Address",
                shouldShowError = state.showEmailError,
                isValid = state.isEmailValid
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.password,
                onValueChange = {},
                onIconClicked = {},
                placeholder = "Password",
                isHidden = state.isPasswordHidden,
                shouldShowError = state.passwordError

            )
            Spacer(modifier = Modifier.height(70.dp))
            CustomButton(
                text = stringResource(R.string.get_started),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(100.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        CustomButton(text = "<", onClick = onBackPress)
    }

}

@Composable
@Preview
fun PreviewRegistrationScreen() {
    RegistrationScreen(onBackPress = {})
}