package com.graeberj.taskman.agenda.domain.repository

import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.util.Resource

interface TaskRepository {
    suspend fun createTask(task: AgendaItem.Task): Resource<Unit>
    suspend fun updateTask(id: String): Resource<Unit>
    suspend fun getTaskById(id: String): Resource<AgendaItem.Task>
    suspend fun deleteTaskById(id: String): Resource<Unit>
}
