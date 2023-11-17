package com.graeberj.taskman.agenda.data.remote.dto

import com.squareup.moshi.Json

data class AgendaResponseDto(
    @field:Json(name = "events")
    val events: List<EventResponseDto>,
    @field:Json(name = "reminders")
    val reminders: List<ReminderDto>,
    @field:Json(name = "tasks")
    val tasks: List<TaskDto>
)
