package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.model.Agenda
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class AgendaRepositoryImpl (
    private val api: AgendaApi
) : AgendaRepository{
    override suspend fun insertReminder(reminder: AgendaItem.Reminder, isEdit: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getReminderById(id: String): AgendaItem.Reminder {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReminderById(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAgenda(date: LocalDate, forceRemote: Boolean): Flow<Agenda> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTask(task: AgendaItem.Task, isEdit: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun changeStatusTask(id: String, isDone: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(id: String): AgendaItem.Task {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTaskById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAttendee(email: String): Result<Attendee?> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventById(id: String): AgendaItem.Event {
        TODO("Not yet implemented")
    }

    override suspend fun insertEvent(event: AgendaItem.Event, isEdit: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEventById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUpcomingItems(): Agenda {
        TODO("Not yet implemented")
    }
}