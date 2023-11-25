package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.auth.domain.util.AuthResult

interface ReminderRepository {
    suspend fun createOrUpdateReminder(reminder: AgendaItem.Reminder, isEdit: Boolean): AuthResult<Unit>
    suspend fun getReminderById(id: String): AuthResult<AgendaItem.Reminder>
    suspend fun deleteReminderById(id: String): AuthResult<Unit>
}
