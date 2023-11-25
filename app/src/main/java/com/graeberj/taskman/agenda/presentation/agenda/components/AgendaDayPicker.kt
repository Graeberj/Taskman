package com.graeberj.taskman.agenda.presentation.agenda.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate

@Composable
fun AgendaDayPicker(
    modifier: Modifier = Modifier,
    date: LocalDate,
    selectedDay: Int = 0,
    onDayClick: (Int) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        for (i in 0..5) {
            AgendaDayPickerItem(
                day = date.plusDays(i.toLong()),
                isSelected = selectedDay == i,
                onDayClick = {
                    onDayClick(i)
                }
            )
        }
    }
}

@Preview
@Composable
fun AgendaDayPickerPreview() {
    AgendaDayPicker(
        date = LocalDate.now(),
        selectedDay = 3,
        onDayClick = {}
    )
}