package com.graeberj.taskman.agenda.data.mapper

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.model.AgendaPhoto
import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.agenda.data.remote.dto.AttendeeDto
import com.graeberj.taskman.agenda.data.remote.dto.EventResponseDto
import com.graeberj.taskman.agenda.data.remote.dto.PhotoDto
import com.graeberj.taskman.agenda.data.remote.dto.ReminderDto
import com.graeberj.taskman.agenda.data.remote.dto.TaskDto
import com.graeberj.taskman.agenda.data.util.toCurrentTime
import java.time.LocalDateTime

fun AttendeeDto.toAttendee(): Attendee {
    return Attendee(
        email = this.email,
        fullName = this.fullName,
        userId = this.userId,
        isGoing = this.isGoing ?: true,
        remindAt = this.remindAt?.toCurrentTime() ?: LocalDateTime.now()
    )
}

fun EventResponseDto.toEvent(): AgendaItem.Event {
    return AgendaItem.Event(
        eventId = id,
        eventTitle = title,
        eventDescription = description,
        eventFromDateTime = from.toCurrentTime(),
        eventToDateTime = to.toCurrentTime(),
        eventRemindAt = remindAt.toCurrentTime(),
        attendees = attendees.map { it.toAttendee() },
        hostId = host,
        isHost = isUserEventCreator,
        photos = photos.map { it.toPhoto() }
    )
}

fun PhotoDto.toPhoto(): AgendaPhoto {
    return AgendaPhoto(url = url, key = key)
}

fun ReminderDto.toReminder(): AgendaItem.Reminder {
    return AgendaItem.Reminder(
        reminderId = id,
        reminderTitle = title,
        reminderDescription = description,
        reminderDateTime = time.toCurrentTime(),
        reminderRemindAt = remindAt.toCurrentTime()
    )
}

fun TaskDto.toTask(): AgendaItem.Task {
    return AgendaItem.Task(
        taskId = id,
        taskTitle = title,
        taskDescription = description,
        taskDateTime = time.toCurrentTime(),
        taskRemindAt = remindAt.toCurrentTime(),
        isDone = isDone

    )
}

