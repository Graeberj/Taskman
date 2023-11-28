package com.graeberj.taskman.agenda.presentation.agenda.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.graeberj.taskman.core.presentation.components.TaskmanHeader
import java.time.LocalDate

@Composable
fun AgendaHeader(
    date: LocalDate,
    name: String,
    onMonthClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TaskmanHeader(modifier = modifier) {
        AgendaHeaderMonthPicker(date = date, onMonthClick = onMonthClick)
        AgendaHeaderProfileName(name = name, onProfileClick = onProfileClick)
    }

}


