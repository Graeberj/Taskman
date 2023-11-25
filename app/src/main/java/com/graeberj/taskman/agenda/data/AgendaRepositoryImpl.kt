package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.mapper.toAttendee
import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.data.remote.dto.AgendaResponseDto
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class AgendaRepositoryImpl(
    private val api: AgendaApi,
    private val serializer: JsonSerializer
) : AgendaRepository {
    override suspend fun getAgenda(date: LocalDate): AuthResult<AgendaResponseDto> {
        val dateTime = LocalDateTime.of(date, LocalTime.now())
        return authenticatedRetrofitCall(serializer){
            val response = api.getAgenda(
                dateTime.atZone(
                    ZoneId.systemDefault()
                ).toInstant().toEpochMilli()
            )
            AuthResult.Authorized(response)
        }
    }

    override suspend fun getAttendee(email: String): AuthResult<Attendee?> {
        return authenticatedRetrofitCall(serializer) {
            val response = api.getAttendee(email).let {
                if (!it.doesUserExist) {
                    null
                } else {
                    it.attendee.toAttendee()
                }
            }
            AuthResult.Authorized(response)
        }

    }
}