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
import com.graeberj.taskman.R
import com.graeberj.taskman.auth.presentation.components.EmailTextField
import com.graeberj.taskman.auth.presentation.components.PasswordTextField
import com.graeberj.taskman.auth.presentation.components.TaskmanButton
import com.graeberj.taskman.auth.presentation.components.TaskmanTextField
import com.graeberj.taskman.auth.presentation.components.TopGreeting

@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onEvent: (RegistrationEvent) -> Unit,
    onBackClick: () -> Unit
) {
    TopGreeting(
        greetingResId = R.string.create_your_account
    ) {
        Column {
            TaskmanTextField(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                value = state.username,
                onValueChange = { onEvent(RegistrationEvent.ValidateName(it)) },
                placeholder = "Name",
                isValid = state.isUsernameValid,
                errorMessage = if (state.isUsernameValid) state.errorMessage else null

            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { onEvent(RegistrationEvent.ValidateEmail(it)) },
                placeholder = "Email Address",
                errorMessage = if (state.isUsernameValid) state.errorMessage else null,
                isValid = state.isEmailValid
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.password,
                onValueChange = { onEvent(RegistrationEvent.ValidatePassword(it)) },
                onIconClicked = { onEvent(RegistrationEvent.TogglePasswordVisibility) },
                placeholder = "Password",
                isHidden = state.isPasswordHidden,
                isValid = state.isPasswordValid,
                errorMessage = if (state.isPasswordValid) state.errorMessage else null,

            )
            Spacer(modifier = Modifier.height(70.dp))
            TaskmanButton(
                text = stringResource(R.string.get_started),
                onClick = { onEvent(RegistrationEvent.Submit) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(100.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        TaskmanButton(text = "<", onClick = { onBackClick() })
    }

}

@Composable
@Preview
fun PreviewRegistrationScreen() {
//    RegistrationScreen()
}