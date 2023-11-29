package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.auth.domain.util.AuthResult

interface TaskRepository {
    suspend fun createOrUpdateTask(task: AgendaItem.Task, isEdit: Boolean): AuthResult<Unit>
    suspend fun getTaskById(id: String): AuthResult<AgendaItem.Task>
    suspend fun deleteTaskById(id: String): AuthResult<Unit>
    suspend fun changeTaskStatus(id: String, isDone: Boolean)
}
