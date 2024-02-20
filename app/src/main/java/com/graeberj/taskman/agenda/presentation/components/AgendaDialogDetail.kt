package com.graeberj.taskman.agenda.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.graeberj.taskman.core.presentation.components.EmailTextField
import com.graeberj.taskman.core.presentation.components.TaskmanButton
import com.graeberj.taskman.ui.theme.Black
import com.graeberj.taskman.ui.theme.White

@Composable
fun AgendaDialogDetail(
    modifier: Modifier = Modifier,
    title: String,
    onClose: () -> Unit,
    onAdd: (String) -> Unit,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isValid: Boolean,
    submitText: String,
    emailAddress: String,
) {
    Dialog(onDismissRequest = onClose) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                modifier = Modifier
                    .align(Alignment.End)
                    .size(32.dp)
                    .clickable {
                        onClose()
                    }
            )
            Text(
                text = title,
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            EmailTextField(
                value = emailAddress,
                onValueChange = { onValueChange(it) },
                placeholder = placeholder,
                isValid = isValid,
                modifier = Modifier.fillMaxWidth(),
                errorMessage = ""
            )
            Spacer(modifier = Modifier.height(30.dp))
            TaskmanButton(
                text = submitText,
                onClick = { onAdd(emailAddress) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
