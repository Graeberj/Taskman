package com.graeberj.taskman.agenda.data.repository

import com.graeberj.taskman.agenda.data.mapper.toEvent
import com.graeberj.taskman.agenda.data.mapper.toEventDto
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.data.remote.dto.EventDto
import com.graeberj.taskman.agenda.domain.repository.EventRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EventRepositoryImpl(
    private val api: AgendaApi,
    private val moshi: Moshi,
    private val serializer: JsonSerializer
) : EventRepository {
    override suspend fun getEventById(id: String): AuthResult<AgendaItem.Event> {
        return authenticatedRetrofitCall(serializer) {
            val response = api.getEventById(id)
            val event = response.toEvent()
            AuthResult.Authorized(event)
        }
    }

    override suspend fun createOrUpdateEvent(
        event: AgendaItem.Event,
        isEdit: Boolean
    ): AuthResult<Unit> {
        val photoList = event.photos.map { photo ->
            val file = File(photo.url)
            val requestFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("photos", file.name, requestFile)
        }

        val jsonAdapter = moshi.adapter(EventDto::class.java)
        val json = jsonAdapter.toJson(event.toEventDto())
        val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val createEventRequestPart =
            MultipartBody.Part.createFormData("createEventRequest", "json", requestBody)

        return authenticatedRetrofitCall(serializer) {
            if (isEdit) {
                api.updateEvent(createEventRequestPart, photoList)
            } else {
                api.createEvent(createEventRequestPart, photoList)
            }
            AuthResult.Authorized(Unit)
        }
    }

    override suspend fun deleteEventById(id: String): AuthResult<Unit> {
        return authenticatedRetrofitCall(serializer) {
            api.deleteEvent(id)
            AuthResult.Authorized(Unit)
        }
    }
}