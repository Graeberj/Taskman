package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.util.Resource

interface ReminderRepository {
    suspend fun createReminder(reminder: AgendaItem.Reminder): Resource<Unit>
    suspend fun getReminderById(id: String): Resource<AgendaItem.Reminder>
    suspend fun deleteReminderById(id: String): Resource<Unit>
}
