package com.igorj.limboapp.screen.home

sealed class HomeEvent {
    data class OnBestPersonClick(val personId: Int): HomeEvent()
    data class OnBestPersonLongPress(val personId: Int): HomeEvent()
    data class OnChapterClick(val chapterId: Int): HomeEvent()
    data class OnChapterLongPress(val chapterId: Int): HomeEvent()
    data class OnBottomNavBarClick(val route: String): HomeEvent()
    object OnFlickersClick: HomeEvent()
    object OnProfileClick: HomeEvent()
}
