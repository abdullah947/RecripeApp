package com.smarthealth.shared.presentation.MainViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel() {

    private val _mainNavEvent = MutableSharedFlow<MainVmNavigationEvent>()
    val maiNavEvents = _mainNavEvent.asSharedFlow()

    fun onEvent(actionEvent: ActionEvent) {
        viewModelScope.launch {
            when (actionEvent) {
                is ActionEvent.Navigate -> {
                    _mainNavEvent.emit(actionEvent.event)
                }
            }
        }
    }

    sealed class ActionEvent {
        data class Navigate(val event: MainVmNavigationEvent) : ActionEvent()
    }
}