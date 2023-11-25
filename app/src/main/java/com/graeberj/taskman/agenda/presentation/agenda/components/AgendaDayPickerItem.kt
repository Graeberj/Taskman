package com.graeberj.taskman.agenda.presentation.agenda.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.ui.theme.Black
import com.graeberj.taskman.ui.theme.Gray
import com.graeberj.taskman.ui.theme.Orange
import java.time.LocalDate

@Composable
fun AgendaDayPickerItem(
    modifier: Modifier = Modifier,
    day: LocalDate,
    isSelected: Boolean = false,
    onDayClick: () -> Unit
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(100))
            .background(if (isSelected) Orange else Color.Transparent).clickable {
                onDayClick()
            }.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.dayOfWeek.name[0].toString(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Black else Gray
        )
        Text(
            text = day.dayOfMonth.toString(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Black
        )
    }
}