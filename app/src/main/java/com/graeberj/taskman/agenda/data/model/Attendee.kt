package com.graeberj.taskman.agenda.data.model

import java.time.LocalDateTime

data class Attendee(
    val email: String,
    val fullName: String,
    val userId: String,
    val isGoing: Boolean,
    val remindAt: LocalDateTime
)
