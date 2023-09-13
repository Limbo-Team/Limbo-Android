package com.igorj.dashboard_domain.model

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes val iconId: Int,
    @DrawableRes val selectedIconId: Int,
)
