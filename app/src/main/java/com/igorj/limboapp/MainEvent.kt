package com.igorj.limboapp

sealed class MainEvent {
    object OnFlickersClick: MainEvent()
    data class OnBottomNavBarClick(val route: String): MainEvent()
    object OnLogoutClick: MainEvent()
}