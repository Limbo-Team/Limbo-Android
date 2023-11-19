package com.igorj.dashboard_presentation.chapters

sealed class ChaptersEvent {
    data class OnChapterClick(val chapterId: Int): ChaptersEvent()
    data class OnChapterLongPress(val chapterId: Int): ChaptersEvent()
    data class OnBottomNavBarClick(val route: String): ChaptersEvent()
    object OnFlickersClick: ChaptersEvent()
    object OnProfileClick: ChaptersEvent()
}
