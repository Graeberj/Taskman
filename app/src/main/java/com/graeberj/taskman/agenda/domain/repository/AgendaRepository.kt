package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.Agenda
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.model.Attendee
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AgendaRepository {
    suspend fun insertReminder(reminder: AgendaItem.Reminder, isEdit: Boolean)
    suspend fun getReminderById(id: String): AgendaItem.Reminder
    suspend fun deleteReminderById(id: String)
    fun getAgenda(date: LocalDate, forceRemote: Boolean): Flow<Agenda>

    suspend fun insertTask(task: AgendaItem.Task, isEdit: Boolean)
    suspend fun changeStatusTask(id: String, isDone: Boolean)
    suspend fun getTaskById(id: String): AgendaItem.Task
    suspend fun deleteTaskById(id: String)

    suspend fun getAttendee(email: String): Result<Attendee?>
    suspend fun getEventById(id: String): AgendaItem.Event
    suspend fun insertEvent(event: AgendaItem.Event, isEdit: Boolean)
    suspend fun deleteEventById(id: String)

    suspend fun getAllUpcomingItems(): Agenda
}
