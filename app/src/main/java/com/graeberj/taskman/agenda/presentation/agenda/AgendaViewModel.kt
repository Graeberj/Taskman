package com.graeberj.taskman.agenda.presentation.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.agenda.domain.repository.AgendaRepository
import com.graeberj.taskman.agenda.domain.usecase.AgendaUseCases
import com.graeberj.taskman.auth.domain.util.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val repository: AgendaRepository,
    private val useCases: AgendaUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AgendaState())
    val state = _state.asStateFlow()


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
                        is AgendaItem.Event -> useCases.deleteEvent(event.agendaItem.id)
                        is AgendaItem.Reminder -> useCases.deleteReminder(event.agendaItem.id)
                        is AgendaItem.Task -> useCases.deleteTask(event.agendaItem.id)
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
            is AgendaEvent.OnItemClick -> if (event.agendaItem is AgendaItem.Task) {
                useCases

            }
        }
    }

    private fun getAgendaForSelectedDate() {
        viewModelScope.launch {
            val result = repository.getAgenda(
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