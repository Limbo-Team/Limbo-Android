package com.igorj.limboapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel(){
    var state = mutableStateOf(MainState())
        private set

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnFlickersClick -> {

            }
        }
    }
}