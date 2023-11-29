package com.graeberj.taskman.agenda.presentation.agenda.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.ui.theme.LightBlue
import com.graeberj.taskman.ui.theme.OffWhite

@Composable
fun AgendaHeaderProfileName(
    modifier: Modifier = Modifier,
    name: String,
    onProfileClick: () -> Unit
) {
    Box(modifier = modifier
        .background(OffWhite, shape = CircleShape)
        .clickable { onProfileClick }
        .padding(8.dp)
    ) {
        Text(
            text = name,
            color = LightBlue,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}
