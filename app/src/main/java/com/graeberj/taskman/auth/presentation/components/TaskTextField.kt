package com.graeberj.taskman.auth.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.graeberj.taskman.R
import com.graeberj.taskman.ui.theme.TaskyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskmanTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    shouldShowError: Boolean,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    trailingIcon: ImageVector? = null,
    trailingIconColor: Color? = null,
    onClick: () -> Unit = {},
    isPassword: Boolean = false,
    isHidden: Boolean = false,
    isValid: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedTrailingIconColor = if (isValid) TaskyGreen else trailingIconColor ?: Color.Gray
        ),
        maxLines = maxLines,
        singleLine = maxLines == 1,
        trailingIcon = {
            TrailingIcon(
                isPassword = isPassword,
                trailingIcon = trailingIcon,
                onClick = onClick,
                isValid = isValid,
                isHidden = isHidden
            )
        },
        isError = shouldShowError,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isHidden) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    shouldShowError: Boolean = false,
    isValid: Boolean = false,
) {
    TaskmanTextField(
        value = value,
        onValueChange = onValueChange,
        shouldShowError = shouldShowError,
        placeholder = placeholder,
        modifier = modifier,
        keyboardType = KeyboardType.Email,
        isValid = isValid
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    shouldShowError: Boolean = false,
    isValid: Boolean = false,
    onIconClicked: () -> Unit,
    isHidden: Boolean = true,
) {
    TaskmanTextField(
        value = value,
        onValueChange = onValueChange,
        shouldShowError = shouldShowError,
        placeholder = placeholder,
        modifier = modifier,
        keyboardType = KeyboardType.Password,
        isValid = isValid,
        isPassword = true,
        onClick = onIconClicked,
        isHidden = isHidden,
    )
}

@Composable
private fun TrailingIcon(
    isPassword: Boolean = false,
    isHidden: Boolean = false,
    trailingIcon: ImageVector? = null,
    isValid: Boolean = false,
    onClick: () -> Unit = {}
) {
    when {
        trailingIcon != null -> {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = stringResource(id = R.string.trailing_icon)
                )
            }
        }

        isPassword -> {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = if (isHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = stringResource(R.string.toggle_password_visibility)
                )
            }
        }

        isValid -> {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.input_is_valid)
            )
        }
    }
}