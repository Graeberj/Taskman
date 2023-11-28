package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.auth.domain.util.AuthResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AgendaRepository {

    suspend fun getAgenda(date: LocalDate): Flow<AuthResult<List<AgendaItem>>>

    suspend fun getAttendee(email: String): AuthResult<Attendee?>
}