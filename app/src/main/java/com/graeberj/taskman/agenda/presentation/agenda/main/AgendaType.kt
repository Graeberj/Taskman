package com.graeberj.taskman.agenda.presentation.agenda.main


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import com.graeberj.taskman.ui.theme.LimeGreen
import com.graeberj.taskman.ui.theme.OffWhite

sealed class AgendaType(val name: String, val color: Color) {
    data object Event : AgendaType("Event", LimeGreen)
    data object Task : AgendaType("Task", Green)
    data object Reminder : AgendaType("Reminder", OffWhite)
}
