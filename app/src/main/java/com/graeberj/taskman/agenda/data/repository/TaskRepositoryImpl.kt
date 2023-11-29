package com.graeberj.taskman.agenda.data.repository

import com.graeberj.taskman.agenda.data.mapper.toTask
import com.graeberj.taskman.agenda.data.mapper.toTaskDto
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.data.remote.api.AgendaApi
import com.graeberj.taskman.agenda.domain.repository.TaskRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import com.graeberj.taskman.auth.domain.util.JsonSerializer
import com.graeberj.taskman.auth.domain.util.authenticatedRetrofitCall

class TaskRepositoryImpl(
    private val api: AgendaApi,
    private val serializer: JsonSerializer
) : TaskRepository {

    override suspend fun createOrUpdateTask(
        task: AgendaItem.Task,
        isEdit: Boolean
    ): AuthResult<Unit> {
        return authenticatedRetrofitCall(serializer) {
            if (isEdit) {
                api.updateTask(task.toTaskDto())
            } else {
                api.createTask(task.toTaskDto())
            }
            AuthResult.Authorized(Unit)
        }
    }

    override suspend fun getTaskById(id: String): AuthResult<AgendaItem.Task> {
        return authenticatedRetrofitCall(serializer) {
            val response = api.getTask(id)
            val task = response.toTask()
            AuthResult.Authorized(task)
        }
    }

    override suspend fun deleteTaskById(id: String): AuthResult<Unit> {
        return authenticatedRetrofitCall(serializer) {
            api.deleteTask(id)
            AuthResult.Authorized(Unit)
        }
    }

    override suspend fun changeTaskStatus(id: String, isDone: Boolean) {
        val task = getTaskById(id).data!!.copy()
        createOrUpdateTask(task, isEdit = true)
    }
}