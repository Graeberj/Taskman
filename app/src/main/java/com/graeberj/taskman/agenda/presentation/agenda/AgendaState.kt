package com.graeberj.taskman.agenda.presentation.agenda

import com.graeberj.taskman.agenda.data.model.AgendaItem
import java.time.LocalDate

data class AgendaState(
    val currentDate: LocalDate = LocalDate.now(),
    val selectedDay: Int = 0,
    val profileName: String = "",
    val agendaItems: List<AgendaItem> = emptyList(),
    val showAgendaOptions: Boolean = false,
    val agendaTypes: List<AgendaType> = listOf(
        AgendaType.Event,
        AgendaType.Task,
        AgendaType.Reminder
    ),
    val selectedAgendaType: AgendaType? = null,
    val showItemOptions: Boolean = false,
    val selectedAgendaItem: AgendaItem? = null,
    val isLoggedOut: Boolean = false,
    val showLogout: Boolean = false
)