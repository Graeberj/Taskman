package com.graeberj.taskman.agenda.presentation.agenda

sealed class AgendaEvent {

    data class OnDayClick(val day: Int) : AgendaEvent()

}
