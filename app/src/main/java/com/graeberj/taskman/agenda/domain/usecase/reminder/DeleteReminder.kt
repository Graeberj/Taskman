package com.graeberj.taskman.agenda.domain.usecase.reminder

import com.graeberj.taskman.agenda.domain.repository.ReminderRepository

class DeleteReminder(private val repository: ReminderRepository) {
    suspend operator fun invoke(id: String) {
        repository.deleteReminderById(id)
    }
}
