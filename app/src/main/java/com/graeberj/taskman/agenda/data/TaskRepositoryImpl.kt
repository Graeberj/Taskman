package com.graeberj.taskman.agenda.data

import com.graeberj.taskman.agenda.data.mapper.toTask
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.data.remote.dto.TaskDto
import com.graeberj.taskman.agenda.data.util.toLong
import com.graeberj.taskman.agenda.domain.repository.TaskRepository
import com.graeberj.taskman.auth.domain.util.UiText
import com.graeberj.taskman.util.Resource
import retrofit2.HttpException
import java.io.IOException

class TaskRepositoryImpl(
    private val api: AgendaApi
) : TaskRepository {

    override suspend fun createTask(task: AgendaItem.Task): Resource<Unit> {
        return try {
            api.updateTask(
                TaskDto(
                    id = task.taskId,
                    title = task.taskTitle,
                    description = task.taskDescription,
                    time = task.taskDateTime.toLong(),
                    remindAt = task.taskRemindAt.toLong(),
                    isDone = false
                )
            )
            Resource.Success(Unit)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

    override suspend fun updateTask(id: String): Resource<Unit> {
        TODO()
    }

    override suspend fun getTaskById(id: String): Resource<AgendaItem.Task> {
        return try {
            val response = api.getTask(id)
            val task = response.toTask()
            Resource.Success(task)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }

    override suspend fun deleteTaskById(id: String): Resource<Unit> {
        return try {
            api.deleteTask(id)
            Resource.Success(Unit)
        } catch (e: HttpException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Invalid Response"))
        } catch (e: IOException) {
            Resource.Error(message = UiText.DynamicString(e.message ?: "Couldn't reach server"))
        }
    }
}