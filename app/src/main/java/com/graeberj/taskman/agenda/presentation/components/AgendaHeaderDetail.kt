package com.graeberj.taskman.agenda.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.R
import com.graeberj.taskman.core.presentation.components.TaskmanHeader
import java.time.LocalDate

@Composable
fun AgendaDetailHeader(
    modifier: Modifier = Modifier,
    text: String,
    date: LocalDate,
    onClose: () -> Unit,
    onEdit: () -> Unit,
    onSave: () -> Unit,
    isEditing: Boolean
) {
    TaskmanHeader(modifier = modifier) {
        IconButton(onClick = { onClose() }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close)
            )
        }
        val headerText = if (isEditing) {
            text.uppercase()
        } else {
            "${date.dayOfMonth} ${date.month.name} ${date.year}"
        }
        Text(
            text = headerText,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        if (isEditing) {
            Text(
                text = stringResource(R.string.save),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onSave() }
            )
        } else {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Default.Edit, stringResource(id = R.string.edit))
            }
        }
    }

}
