package com.graeberj.taskman.agenda.presentation.event

import java.time.LocalDate
import java.time.LocalTime

sealed class EventEvent {
    data object OnEdit : EventEvent()
    data object OnSave : EventEvent()
    data object OnDelete : EventEvent()
    data object OnOpen : EventEvent()
    data object OnClose : EventEvent()
    data class OnUpdate(val title: String, val description: String) : EventEvent()
    data class OnFromDate(val date: LocalDate) : EventEvent()
    data class OnToDate(val date: LocalDate) : EventEvent()
    data class OnFromTime(val time: LocalTime) : EventEvent()
    data class OnToTime(val time: LocalTime) : EventEvent()
    data class OnFilterType(val type: EventFilterType) : EventEvent()
    data class OnValueChange(val email: String) : EventEvent()
    data class OnAddVisitor(val email: String) : EventEvent()

}
