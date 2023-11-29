package com.graeberj.taskman.agenda.presentation.agenda

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.agenda.presentation.agenda.components.AgendaDayPicker
import com.graeberj.taskman.agenda.presentation.agenda.components.AgendaHeader
import com.graeberj.taskman.core.presentation.components.TaskmanDropdown
import com.graeberj.taskman.core.presentation.components.TopGreeting
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AgendaScreen(
    state: AgendaState,
    onEvent: (AgendaEvent) -> Unit,
    onLogout: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        onEvent(AgendaEvent.OnRefreshAgenda)
    }
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
            Box(modifier = Modifier.fillMaxSize()) {
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


        }
    }
}



