package com.graeberj.taskman.agenda.data.remote.api

import com.graeberj.taskman.agenda.data.remote.dto.AgendaResponseDto
import com.graeberj.taskman.agenda.data.remote.dto.AttendeeExistResponseDto
import com.graeberj.taskman.agenda.data.remote.dto.EventDto
import com.graeberj.taskman.agenda.data.remote.dto.ReminderDto
import com.graeberj.taskman.agenda.data.remote.dto.TaskDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface AgendaApi {

    @GET("/event")
    suspend fun getEventById(@Query("eventId") eventId: String): EventDto

    @Multipart
    @POST("/event")
    suspend fun createEvent(
        @Part createEventRequest: MultipartBody.Part,
        @Part photos: List<MultipartBody.Part>
    ): EventDto

    @Multipart
    @POST("/event")
    suspend fun updateEvent(
        @Part updateEventRequest: MultipartBody.Part,
        @Part photos: List<MultipartBody.Part>
    ): EventDto

    @DELETE("/event")
    suspend fun deleteEvent(@Query("eventId") eventId: String)

    @POST("/reminder")
    suspend fun createReminder(@Body body: ReminderDto)

    @PUT("/reminder")
    suspend fun updateReminder(@Body body: ReminderDto)

    @DELETE("/reminder")
    suspend fun deleteReminder(@Query("reminderId") id: String)

    @POST("/task")
    suspend fun createTask(@Body body: TaskDto)

    @PUT("/task")
    suspend fun updateTask(@Body body: TaskDto)

    @DELETE("/task")
    suspend fun deleteTask(@Query("taskId") id: String)

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("time") time: Long,
        @Query("timezone") timeZone: String
    ): AgendaResponseDto

    @GET("/attendee")
    suspend fun getAttendee(@Query("email") email: String): AttendeeExistResponseDto

}