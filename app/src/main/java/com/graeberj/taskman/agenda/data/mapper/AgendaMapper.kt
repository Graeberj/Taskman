package com.graeberj.taskman.agenda.data.mapper

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.model.AgendaPhoto
import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.agenda.data.remote.dto.AgendaResponseDto
import com.graeberj.taskman.agenda.data.remote.dto.AttendeeDto
import com.graeberj.taskman.agenda.data.remote.dto.EventDto
import com.graeberj.taskman.agenda.data.remote.dto.EventResponseDto
import com.graeberj.taskman.agenda.data.remote.dto.PhotoDto
import com.graeberj.taskman.agenda.data.remote.dto.ReminderDto
import com.graeberj.taskman.agenda.data.remote.dto.TaskDto
import com.graeberj.taskman.agenda.data.util.toCurrentTime
import com.graeberj.taskman.agenda.data.util.toLong
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

fun AgendaItem.Event.toEventDto(): EventDto {
    return EventDto(
        id = eventId,
        title = eventTitle,
        description = eventDescription,
        from = eventFromDateTime.toLong(),
        to = eventToDateTime.toLong(),
        remindAt = remindAt.toLong(),
        attendeeIds = attendees.map { it.userId }

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

fun AgendaItem.Reminder.toReminderDto(): ReminderDto {
    return ReminderDto(

        id = reminderId,
        title = reminderTitle,
        description = reminderDescription,
        time = reminderDateTime.toLong(),
        remindAt = reminderRemindAt.toLong()
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

fun AgendaItem.Task.toTaskDto(): TaskDto {
    return TaskDto(
        id = taskId,
        title = taskTitle,
        description = taskDescription,
        time = taskDateTime.toLong(),
        remindAt = taskRemindAt.toLong(),
        isDone = isDone

    )
}

fun AgendaResponseDto.toAgendaItemsList(): List<AgendaItem> {
    val events = this.events.map { it.toEvent() }
    val reminders = this.reminders.map { it.toReminder() }
    val tasks = this.tasks.map { it.toTask() }

    return events + reminders + tasks
}

