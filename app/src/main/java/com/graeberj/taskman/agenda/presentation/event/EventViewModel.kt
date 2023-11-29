package com.graeberj.taskman.agenda.presentation.event

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.graeberj.taskman.agenda.domain.repository.EventRepository
import com.graeberj.taskman.auth.domain.usecase.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: EventRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(EventState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("date")?.let {
            _state.update {
                it.copy(
                    fromDate = LocalDateTime.parse(it.toString()).toLocalDate(),
                    fromTime = LocalDateTime.parse(it.toString()).toLocalTime(),
                    toDate = state.value.toDate,
                    toTime = state.value.toTime.plusMinutes(30)
                )
            }
        }
        //TODO() check if event is null if so create new event else edit existing event
    }

    fun onEvent(event: EventEvent) {
        when (event) {
            is EventEvent.OnAddVisitor -> TODO()
            EventEvent.OnClose -> TODO()
            EventEvent.OnDelete -> TODO()
            EventEvent.OnEdit -> TODO()
            is EventEvent.OnFilterType -> TODO()
            is EventEvent.OnFromDate -> TODO()
            is EventEvent.OnFromTime -> TODO()
            EventEvent.OnOpen -> TODO()
            EventEvent.OnSave -> TODO()
            is EventEvent.OnToDate -> TODO()
            is EventEvent.OnToTime -> TODO()
            is EventEvent.OnUpdate -> TODO()
            is EventEvent.OnValueChange -> TODO()
        }
    }


}