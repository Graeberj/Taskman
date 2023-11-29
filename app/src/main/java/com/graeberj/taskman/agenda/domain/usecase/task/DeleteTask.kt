package com.graeberj.taskman.agenda.domain.usecase.task

import com.graeberj.taskman.agenda.domain.repository.TaskRepository

class DeleteTask(private val repository: TaskRepository) {
    suspend operator fun invoke(id: String) {
        repository.deleteTaskById(id)
    }

}
