package com.graeberj.taskman.agenda.presentation.agenda

import androidx.lifecycle.ViewModel
import com.graeberj.taskman.agenda.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AgendaState())
    val state = _state.asStateFlow()

    fun onEvent(event: AgendaEvent) {

    }
}