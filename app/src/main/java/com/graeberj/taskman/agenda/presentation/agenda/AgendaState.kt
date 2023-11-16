package com.graeberj.taskman.agenda.presentation.agenda

import java.time.LocalDate

data class AgendaState(
    val currentDate: LocalDate = LocalDate.now()
)