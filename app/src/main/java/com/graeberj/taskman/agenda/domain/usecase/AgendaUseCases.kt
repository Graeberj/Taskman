package com.graeberj.taskman.agenda.domain.usecase

import com.graeberj.taskman.agenda.domain.usecase.event.DeleteEvent
import com.graeberj.taskman.agenda.domain.usecase.reminder.DeleteReminder
import com.graeberj.taskman.agenda.domain.usecase.task.ChangeTaskStatus
import com.graeberj.taskman.agenda.domain.usecase.task.DeleteTask

data class AgendaUseCases(
    val deleteReminder: DeleteReminder,
    val deleteTask: DeleteTask,
    val deleteEvent: DeleteEvent,
    val changeTaskStatus: ChangeTaskStatus
)
