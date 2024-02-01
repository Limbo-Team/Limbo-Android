package com.igorj.limboapp.model

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes val iconId: Int,
    @DrawableRes val selectedIconId: Int,
)
