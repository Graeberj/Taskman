package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.auth.domain.util.AuthResult

interface EventRepository {
    suspend fun getEventById(id: String): AuthResult<AgendaItem.Event>
    suspend fun createOrUpdateEvent(event: AgendaItem.Event, isEdit: Boolean): AuthResult<Unit>
    suspend fun deleteEventById(id: String): AuthResult<Unit>
}
