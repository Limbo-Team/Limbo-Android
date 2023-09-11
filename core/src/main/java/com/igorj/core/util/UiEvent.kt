package com.igorj.core.util

sealed class UiEvent {
    object OnNavigate : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}
