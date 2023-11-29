package com.graeberj.taskman.agenda.presentation.agenda.main

import com.graeberj.taskman.agenda.data.model.AgendaItem
import java.time.LocalDate

sealed class AgendaEvent {

    data class OnDayClick(val day: Int) : AgendaEvent()
    data class OnDeleteItem(val agendaItem: AgendaItem) : AgendaEvent()
    data class OnDateSelected(val date: LocalDate) : AgendaEvent()
    data object OnDismiss : AgendaEvent()
    data object OnAddClick : AgendaEvent()
    data object OnLogout : AgendaEvent()
    data object OnRefreshAgenda : AgendaEvent()
    data class OnItemClick(val agendaItem: AgendaItem) : AgendaEvent()
    data class OnItemOptionsClick(val agendaItem: AgendaItem) : AgendaEvent()


}
