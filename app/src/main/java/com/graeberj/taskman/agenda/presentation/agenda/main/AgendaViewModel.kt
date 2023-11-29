package com.graeberj.taskman.agenda.presentation.agenda.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import com.graeberj.taskman.agenda.domain.repository.EventRepository
import com.graeberj.taskman.agenda.domain.repository.ReminderRepository
import com.graeberj.taskman.agenda.domain.repository.TaskRepository
import com.graeberj.taskman.auth.domain.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val agendaRepository: AgendaRepository,
    private val eventRepository: EventRepository,
    private val reminderRepository: ReminderRepository,
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AgendaState())
    val state = _state.asStateFlow()


    init {
        onEvent(AgendaEvent.OnRefreshAgenda)
    }

    fun onEvent(event: AgendaEvent) {
        when (event) {
            AgendaEvent.OnAddClick -> {
                _state.update { it.copy(showAgendaOptions = true) }
            }

            is AgendaEvent.OnDateSelected -> {
                _state.update {
                    it.copy(currentDate = event.date, selectedDay = 0)
                }
                getAgendaForSelectedDate()
            }

            is AgendaEvent.OnDayClick -> {
                _state.update {
                    it.copy(
                        selectedDay = event.day
                    )
                }
                getAgendaForSelectedDate()
            }

            is AgendaEvent.OnDeleteItem -> {
                viewModelScope.launch {
                    when (event.agendaItem) {
                        is AgendaItem.Event -> eventRepository.deleteEventById(event.agendaItem.id)
                        is AgendaItem.Reminder -> reminderRepository.deleteReminderById(event.agendaItem.id)
                        is AgendaItem.Task -> taskRepository.deleteTaskById(event.agendaItem.id)
                    }
                }
            }

            AgendaEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        showLogout = false,
                        showAgendaOptions = false,
                        showItemOptions = false
                    )
                }
            }

            AgendaEvent.OnLogout -> {
                _state.update { it.copy(showLogout = true) }
            }

            AgendaEvent.OnRefreshAgenda -> getAgendaForSelectedDate()

            is AgendaEvent.OnItemClick -> {
                viewModelScope.launch {
                    if (event.agendaItem is AgendaItem.Task) {
                        taskRepository.changeTaskStatus(
                            event.agendaItem.id,
                            !event.agendaItem.isDone
                        )
                        getAgendaForSelectedDate()
                    }
                }
            }

            is AgendaEvent.OnItemOptionsClick -> {
                _state.update {
                    it.copy(
                        selectedAgendaItem = event.agendaItem,
                        showItemOptions = true
                    )
                }
            }
        }
    }

    private fun getAgendaForSelectedDate() {
        viewModelScope.launch {
            val result = agendaRepository.getAgenda(
                state.value.currentDate.plusDays(state.value.selectedDay.toLong())
            )
            result.collect { agenda ->
                when (agenda) {
                    is AuthResult.Authorized -> {
                        _state.update {
                            it.copy(
                                agendaItems = agenda.data!!.sortedBy { data -> data.time }
                            )
                        }
                    }

                    is AuthResult.Error -> {
                        _state.update { it.copy(errorMessage = agenda.message.toString()) }
                    }

                    is AuthResult.Unauthorized -> {
                        _state.update {
                            it.copy(
                                errorMessage = "User is not authorised to view this agenda"
                            )
                        }
                    }
                }
            }
        }
    }
}