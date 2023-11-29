package com.graeberj.taskman.agenda.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReminderDto(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "time")
    val time: Long,
    @field:Json(name = "remindAt")
    val remindAt: Long
)
