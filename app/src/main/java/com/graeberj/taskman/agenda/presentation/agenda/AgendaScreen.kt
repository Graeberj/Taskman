package com.graeberj.taskman.agenda.presentation.agenda

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AgendaScreen(
    state: AgendaState,
    onEvent: (AgendaEvent) -> Unit
){
    Text(text = "this is the home screen, User is Logged in")
}