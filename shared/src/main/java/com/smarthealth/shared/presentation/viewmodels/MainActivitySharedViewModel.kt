package com.smarthealth.shared.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivitySharedViewModel : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun navigate(screen: Route) = viewModelScope.launch {
        _event.emit(Event.NavigateToScreen(screen))
    }

    sealed class Event {
        class NavigateToScreen(val screen: Route) : Event()
    }
}

interface Route