package com.graeberj.taskman.agenda.presentation.agenda.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.presentation.agenda.main.components.AgendaDayPicker
import com.graeberj.taskman.agenda.presentation.agenda.main.components.AgendaHeader
import com.graeberj.taskman.agenda.presentation.agenda.main.components.AgendaItem
import com.graeberj.taskman.core.presentation.components.TaskmanButton
import com.graeberj.taskman.core.presentation.components.TaskmanDropdown
import com.graeberj.taskman.core.presentation.components.TopGreeting
import com.graeberj.taskman.ui.theme.LimeGreen
import com.graeberj.taskman.ui.theme.OffWhite
import com.graeberj.taskman.ui.theme.TaskyGreen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun AgendaScreen(
    state: AgendaState,
    onEvent: (AgendaEvent) -> Unit,
    onLogout: () -> Unit,
    options: (AgendaItemOptions, AgendaItem) -> Unit,
    redirect: (AgendaType, LocalDateTime) -> Unit
) {
    val context = LocalContext.current

    val isLoggedOut = state.isLoggedOut
    LaunchedEffect(key1 = isLoggedOut) {
        if (isLoggedOut) {
            onLogout()
        }
    }
    val datePickerState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = datePickerState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            onEvent(AgendaEvent.OnDateSelected(date))
        }
    }
    TopGreeting(
        header = {
            AgendaHeader(
                date = state.currentDate,
                name = state.profileName,
                onMonthClick = { datePickerState.show() },
                onProfileClick = { onEvent(AgendaEvent.OnLogout) }
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                TaskmanDropdown(
                    items = listOf("Logout"),
                    onItemSelected = { onLogout },
                    showDropdown = state.showLogout,
                    onDismiss = { onEvent(AgendaEvent.OnDismiss) }
                )
            }
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            AgendaDayPicker(
                date = state.currentDate,
                selectedDay = state.selectedDay,
                onDayClick = {
                    onEvent(AgendaEvent.OnDayClick(it))
                })

            val date = state.currentDate.plusDays(state.selectedDay.toLong())
            val dateTitle = if (date == LocalDate.now()) {
                "Today"
            } else {
                val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM uuuu")
                date.format(dateFormatter)
            }
            Text(
                text = dateTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(modifier = Modifier.height(24.dp)) {
                items(state.agendaItems) { item ->
                    AgendaItem(
                        item = item,
                        color = when (item) {
                            is AgendaItem.Reminder -> OffWhite
                            is AgendaItem.Task -> TaskyGreen
                            is AgendaItem.Event -> LimeGreen
                        },
                        onOptionsClick = { onEvent(AgendaEvent.OnItemOptionsClick(item)) },
                        onItemClick = { onEvent(AgendaEvent.OnItemClick(item)) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        val itemOptions = AgendaItemOptions.values()
        val itemOptionNames = remember {
            itemOptions.map { it.type.getString(context) }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            TaskmanDropdown(
                items = itemOptionNames,
                onItemSelected = {
                    val selectedOption = itemOptions[it]
                    state.selectedAgendaItem?.let { item ->
                        if (selectedOption == AgendaItemOptions.DELETE) {
                            onEvent(AgendaEvent.OnDeleteItem(item))
                        } else {
                            options(selectedOption, item)
                        }
                    }
                },
                showDropdown = state.showItemOptions,
                onDismiss = { onEvent(AgendaEvent.OnDismiss) }
            )
        }

        val agendaTypeNames = remember {
            state.agendaTypes.map { it.name }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            TaskmanButton(text = "+", onClick = {
                onEvent(AgendaEvent.OnAddClick)
            })
            TaskmanDropdown(
                items = agendaTypeNames,
                onItemSelected = {
                    redirect(
                        state.agendaTypes[it],
                        LocalDateTime.of(
                            state.currentDate.plusDays(state.selectedDay.toLong()),
                            LocalTime.now()
                        )
                    )
                },
                showDropdown = state.showAgendaOptions,
                onDismiss = { onEvent(AgendaEvent.OnDismiss) })
        }
    }
}



