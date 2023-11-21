package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.mapper.toReminder
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.data.remote.dto.ReminderDto
import com.graeberj.taskman.agenda.data.util.toLong
import com.graeberj.taskman.agenda.domain.repository.ReminderRepository
import com.graeberj.taskman.auth.domain.util.UiText
import com.graeberj.taskman.util.Resource
import retrofit2.HttpException
import java.io.IOException

class ReminderRepositoryImpl(
    private val api: AgendaApi
) : ReminderRepository {
    override suspend fun getReminderById(id: String): Resource<AgendaItem.Reminder> {
        return try {
            val response = api.getReminder(id)
            val reminder = response.toReminder()
            Resource.Success(reminder)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

    override suspend fun createReminder(reminder: AgendaItem.Reminder): Resource<Unit> {
        return try {
            api.createReminder(
                ReminderDto(
                    id = reminder.reminderId,
                    title = reminder.reminderTitle,
                    description = reminder.reminderDescription,
                    time = reminder.reminderDateTime.toLong(),
                    remindAt = reminder.reminderRemindAt.toLong()
                )
            )
            Resource.Success(Unit)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

    override suspend fun deleteReminderById(id: String): Resource<Unit> {
        return try {
            api.deleteReminder(id)
            Resource.Success(Unit)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }
}