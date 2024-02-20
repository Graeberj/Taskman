package com.graeberj.taskman.agenda.presentation.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.graeberj.taskman.R
import com.graeberj.taskman.agenda.presentation.components.AgendaColor
import com.graeberj.taskman.agenda.presentation.components.AgendaDescription
import com.graeberj.taskman.agenda.presentation.components.AgendaDetailHeader
import com.graeberj.taskman.agenda.presentation.components.AgendaDialogDetail
import com.graeberj.taskman.agenda.presentation.components.AgendaTimeSelector
import com.graeberj.taskman.agenda.presentation.components.AgendaTitle
import com.graeberj.taskman.core.presentation.components.TopGreeting
import com.graeberj.taskman.ui.theme.LimeGreen
import com.graeberj.taskman.ui.theme.OffWhite

@Composable
fun EventScreen(
    state: EventState,
    onEvent: (EventEvent) -> Unit,
    title: String,
    description: String,
    onClose: () -> Unit,
    edit: (id: String, title: String, body: String, size: Int) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = title) {
        onEvent(EventEvent.OnUpdate(title, description))
    }

    LaunchedEffect(key1 = state.shouldExit) {
        if (state.shouldExit) {
            onClose()
        }
    }
    TopGreeting(
        header = {
            AgendaDetailHeader(
                text = "Edit Event",
                date = state.fromDate,
                onClose = onClose,
                onEdit = { onEvent(EventEvent.OnEdit) },
                onSave = { onEvent(EventEvent.OnSave) },
                isEditing = state.isEditing
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (state.showDialog) {
                AgendaDialogDetail(
                    title = "Add Visitor",
                    onClose = { onEvent(EventEvent.OnClose) },
                    onAdd = { onEvent(EventEvent.OnAddVisitor(it)) },
                    placeholder = stringResource(R.string.email_address),
                    isValid = state.isValidDialog,
                    submitText = "Add",
                    emailAddress = state.dialogEmail,
                    onValueChange = { onEvent(EventEvent.OnValueChange(it)) },
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                AgendaColor(
                    text = "Event",
                    color = LimeGreen,
                    modifier = Modifier.padding(top = 14.dp)
                )
            }
            item {
                AgendaTitle(title = state.title, isEditable = state.isEditing, onClick = {
                    edit(
                        "event_title",
                        context.getString(R.string.edit_title),
                        state.title,
                        26
                    )
                })
                Divider(color = OffWhite)
            }
            item {
                AgendaDescription(
                    description = state.description,
                    isEditable = state.isEditing,
                    onClick = {
                        edit(
                            "event_description",
                            "Edit Description",
                            state.description,
                            16
                        )
                    }
                )
            }
            //TODO() add photo selector
            item {
                AgendaTimeSelector(
                    text = "From",
                    date = state.fromDate,
                    time = state.fromTime,
                    isEditable = state.isEditing,
                    onTimeSelected = {
                        onEvent(EventEvent.OnFromTime(it))
                    },
                    onDateSelected = {
                        onEvent(EventEvent.OnFromDate(it))
                    }
                )
                Divider(color = OffWhite)
            }
            item {
                AgendaTimeSelector(
                    text = "To",
                    date = state.toDate,
                    time = state.toTime,
                    isEditable = state.isEditing,
                    onTimeSelected = {
                        onEvent(EventEvent.OnToTime(it))
                    },
                    onDateSelected = {
                        onEvent(EventEvent.OnToDate(it))
                    }
                )
                Divider(color = OffWhite)
            }
        }

    }
}

