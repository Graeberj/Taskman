package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.agenda.data.remote.dto.AgendaResponseDto
import com.graeberj.taskman.auth.domain.util.AuthResult
import java.time.LocalDate

interface AgendaRepository {

    suspend fun getAgenda(date: LocalDate): AuthResult<AgendaResponseDto>

    suspend fun getAttendee(email: String): AuthResult<Attendee?>
}