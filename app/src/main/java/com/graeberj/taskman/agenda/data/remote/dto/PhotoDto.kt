package com.graeberj.taskman.agenda.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @field:Json(name = "key")
    val key: String,
    @field:Json(name = "url")
    val url: String
)
