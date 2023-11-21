package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.util.Resource

interface EventRepository {
    suspend fun getEventById(id: String): Resource<AgendaItem.Event>
    suspend fun createEvent(event: AgendaItem.Event): Resource<Unit>
    suspend fun deleteEventById(id: String): Resource<Unit>
}
