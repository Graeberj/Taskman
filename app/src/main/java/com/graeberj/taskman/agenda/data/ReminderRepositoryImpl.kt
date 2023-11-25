package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.mapper.toReminder
import com.graeberj.taskman.agenda.data.mapper.toReminderDto
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.domain.repository.ReminderRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall

class ReminderRepositoryImpl(
    private val api: AgendaApi,
    private val serializer: JsonSerializer
) : ReminderRepository {
    override suspend fun getReminderById(id: String): AuthResult<AgendaItem.Reminder> {
        return authenticatedRetrofitCall(serializer) {
            val response = api.getReminder(id)
            val reminder = response.toReminder()
            AuthResult.Authorized(reminder)
        }
    }

    override suspend fun createOrUpdateReminder(
        reminder: AgendaItem.Reminder,
        isEdit: Boolean
    ): AuthResult<Unit> {
        return authenticatedRetrofitCall(serializer) {
            if (isEdit) {
                api.updateReminder(reminder.toReminderDto())
            } else {
                api.createReminder(reminder.toReminderDto())
            }
            AuthResult.Authorized(Unit)
        }
    }

    override suspend fun deleteReminderById(id: String): AuthResult<Unit> {
        return authenticatedRetrofitCall(serializer) {
            api.deleteReminder(id)
            AuthResult.Authorized(Unit)
        }
    }
}