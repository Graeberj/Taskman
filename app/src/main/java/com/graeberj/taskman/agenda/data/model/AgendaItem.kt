package com.graeberj.taskman.agenda.data.model

import java.time.LocalDateTime

sealed class AgendaItem(
    val id: String,
    val title: String,
    val description: String,
    val time: LocalDateTime,
    val remindAt: LocalDateTime
) {
    data class Reminder(
        val reminderId: String,
        val reminderTitle: String,
        val reminderDescription: String,
        val reminderDateTime: LocalDateTime,
        val reminderRemindAt: LocalDateTime
    ) : AgendaItem(
        id = reminderId,
        title = reminderTitle,
        description = reminderDescription,
        time = reminderDateTime,
        remindAt = reminderRemindAt
    )

    data class Task(
        val taskId: String,
        val taskTitle: String,
        val taskDescription: String,
        val taskDateTime: LocalDateTime,
        val taskRemindAt: LocalDateTime,
        val isDone: Boolean
    ) : AgendaItem(
        id = taskId,
        title = taskTitle,
        description = taskDescription,
        time = taskDateTime,
        remindAt = taskRemindAt
    )

    data class Event(
        val eventId: String,
        val eventTitle: String,
        val eventDescription: String,
        val eventFromDateTime: LocalDateTime,
        val eventToDateTime: LocalDateTime,
        val eventRemindAt: LocalDateTime,
        val attendees: List<Attendee>,
        val hostId: String,
        val isHost: Boolean,
        val photos: List<AgendaPhoto>
    ) : AgendaItem(
        id = eventId,
        title = eventTitle,
        description = eventDescription,
        time = eventFromDateTime,
        remindAt = eventRemindAt
    )
}
