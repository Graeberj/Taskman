package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.mapper.toAgendaItemsList
import com.graeberj.taskman.agenda.data.mapper.toAttendee
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.model.Attendee
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class AgendaRepositoryImpl(
    private val api: AgendaApi,
    private val serializer: JsonSerializer
) : AgendaRepository {
    override suspend fun getAgenda(date: LocalDate): Flow<AuthResult<List<AgendaItem>>> {
        val dateTime = LocalDateTime.of(date, LocalTime.now())
        return flow {
            authenticatedRetrofitCall(serializer) {
                val response = api.getAgenda(
                    dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                )
                AuthResult.Authorized(response.toAgendaItemsList())

            }
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