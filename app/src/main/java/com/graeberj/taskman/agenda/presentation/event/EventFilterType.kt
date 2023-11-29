package com.graeberj.taskman.agenda.presentation.event

import com.graeberj.taskman.R
import com.graeberj.taskman.auth.domain.util.UiText

enum class EventFilterType(val type: UiText) {
    ALL(UiText.StringResource(R.string.all)),
    GOING(UiText.StringResource(R.string.going)),
    NOT_GOING(UiText.StringResource(R.string.not_going));

    companion object {
        fun from(type: String): EventFilterType? =
            values().find { it.name.lowercase() == type.lowercase() }
    }
}
