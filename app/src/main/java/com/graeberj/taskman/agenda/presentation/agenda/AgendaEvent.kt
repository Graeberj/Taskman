package com.graeberj.taskman.agenda.presentation.agenda

import java.time.LocalDate

sealed class AgendaEvent {

    data class OnDayClick(val day: Int) : AgendaEvent()
    data class OnDeleteItem(val day: Int) : AgendaEvent()
    data class OnDateSelected(val date: LocalDate) : AgendaEvent()
    data object OnDismiss : AgendaEvent()
    data object OnAddClick : AgendaEvent()
    data object OnLogout : AgendaEvent()
    data object OnRefreshAgenda : AgendaEvent()
    data class OnDatePickerClick(val show: Boolean) : AgendaEvent()



}
