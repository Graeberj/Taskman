package com.graeberj.taskman.agenda.domain.usecase.event

import com.graeberj.taskman.agenda.domain.repository.EventRepository

class DeleteEvent(private val repository: EventRepository) {
    suspend operator fun invoke(id: String) {
        repository.deleteEventById(id)
    }
}
