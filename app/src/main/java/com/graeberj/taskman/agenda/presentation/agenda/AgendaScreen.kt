package com.graeberj.taskman.agenda.presentation.agenda

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.agenda.presentation.agenda.components.AgendaDayPicker
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AgendaScreen(
    state: AgendaState,
    onEvent: (AgendaEvent) -> Unit,
    modifier: Modifier = Modifier
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



