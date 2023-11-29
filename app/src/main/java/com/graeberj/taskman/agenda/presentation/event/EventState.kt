package com.graeberj.taskman.agenda.presentation.event

import com.graeberj.taskman.agenda.data.model.Attendee
import java.time.LocalDate
import java.time.LocalTime

data class EventState(
    val isEditing: Boolean = true,
    val id: String? = null,
    val title: String = "New Event",
    val description: String = "Description",
    val fromDate: LocalDate = LocalDate.now(),
    val fromTime: LocalTime = LocalTime.now(),
    val toDate: LocalDate = LocalDate.now(),
    val toTime: LocalTime = LocalTime.now(),
    val isSelectingNotificationReminder: Boolean = false,
    val shouldExit: Boolean = false,
    val selectedFilterType: EventFilterType = EventFilterType.ALL,
    val attendees: List<Attendee> = emptyList(),
    val hostId: String = "",
    val isHost: Boolean = true,
    val showDialog: Boolean = false,
    val isValidDialog: Boolean = false,
    val isErrorDialog: Boolean = false,
    val dialogEmail: String = "",
    val isLoadingDialog: Boolean = false
)
