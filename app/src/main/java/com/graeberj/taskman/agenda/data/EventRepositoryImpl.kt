package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.mapper.toEvent
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.data.remote.dto.EventDto
import com.graeberj.taskman.agenda.data.util.toLong
import com.graeberj.taskman.agenda.domain.repository.EventRepository
import com.graeberj.taskman.auth.domain.util.UiText
import com.graeberj.taskman.util.Resource
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class EventRepositoryImpl(
    private val api: AgendaApi,
    private val moshi: Moshi
) : EventRepository {
    override suspend fun getEventById(id: String): Resource<AgendaItem.Event> {
        return try {
            val response = api.getEventById(id)
            val event = response.toEvent()
            Resource.Success(event)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

    override suspend fun createEvent(event: AgendaItem.Event): Resource<Unit> {
        val photoList = event.photos.map { photo ->
            val file = File(photo.url)
            val requestFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("photos", file.name, requestFile)
        }
        val createdEvent = EventDto(
            id = event.eventId,
            title = event.eventTitle,
            description = event.eventDescription,
            from = event.eventFromDateTime.toLong(),
            to = event.eventToDateTime.toLong(),
            remindAt = event.remindAt.toLong(),
            attendeeIds = event.attendees.map { it.userId }
        )
        val jsonAdapter = moshi.adapter(EventDto::class.java)
        val json = jsonAdapter.toJson(createdEvent)
        val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val createEventRequestPart =
            MultipartBody.Part.createFormData("createEventRequest", "json", requestBody)
        return try {
            api.createEvent(
                createEventRequestPart,
                photoList
            )
            Resource.Success(Unit)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

    override suspend fun deleteEventById(id: String): Resource<Unit> {
        return try {
            api.deleteEvent(id)
            Resource.Success(Unit)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

}