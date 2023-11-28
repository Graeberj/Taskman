package com.graeberj.taskman.agenda.domain.usecase.task

import com.graeberj.taskman.agenda.domain.repository.TaskRepository

class ChangeTaskStatus(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: String, isDone: Boolean) {
        repository.changeTaskStatus(id, isDone)
    }
}
