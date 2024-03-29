package com.graeberj.taskman.agenda.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.ui.theme.Black
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun AgendaTimeSelector(
    modifier: Modifier = Modifier,
    text: String,
    date: LocalDate,
    time: LocalTime,
    isEditable: Boolean,
    onTimeSelected: (LocalTime) -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {

    val datepickerState = rememberMaterialDialogState()
    val timepickerState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = timepickerState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
            onTimeSelected(time)
        }
    }

    MaterialDialog(
        dialogState = datepickerState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            onDateSelected(date)
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(9f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, fontSize = 16.sp, color = Black)
            Row(
                modifier = Modifier
                    .then(
                        if (isEditable) {
                            Modifier.clickable {
                                timepickerState.show()
                            }
                        } else Modifier
                    )
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                val timeFormatted = time.format(timeFormatter)
                Text(
                    text = timeFormatted,
                    fontSize = 16.sp,
                    color = Black
                )
                if (isEditable) {
                    Icon(
                        imageVector = Icons.Default.NavigateNext,
                        contentDescription = stringResource(com.graeberj.taskman.R.string.edit_time)
                    )
                }
            }
            val dateFormatter = DateTimeFormatter.ofPattern("MMM dd uuuu")
            val formatted = date.format(dateFormatter)
            Text(
                text = formatted,
                fontSize = 16.sp,
                color = Black,
                modifier = Modifier
                    .then(
                        if (isEditable) {
                            Modifier.clickable {
                                datepickerState.show()
                            }
                        } else Modifier
                    )
                    .padding(start = 10.dp, end = 10.dp)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            if (isEditable) {
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = stringResource(com.graeberj.taskman.R.string.edit_time)
                )
            }
        }
    }
}