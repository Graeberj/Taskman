package com.graeberj.taskman.agenda.presentation.agenda

import com.graeberj.taskman.R
import com.graeberj.taskman.auth.domain.util.UiText

enum class AgendaItemOptions(val type: UiText) {
    OPEN(UiText.StringResource(R.string.open)),
    EDIT(UiText.StringResource(R.string.edit)),
    DELETE(UiText.StringResource(R.string.delete));

    companion object {
        fun from(type: String): AgendaItemOptions? =
            values().find { it.name.lowercase() == type.lowercase() }
    }

}
