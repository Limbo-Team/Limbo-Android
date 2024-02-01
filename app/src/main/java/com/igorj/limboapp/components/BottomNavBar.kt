package com.igorj.limboapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.igorj.limboapp.ui.theme.DarkGray
import com.igorj.limboapp.model.BottomNavItem
import com.igorj.limboapp.R

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    selectedItemRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier.height(66.dp),
        backgroundColor = DarkGray
    ) {
        items.forEach { item ->
            val selected = item.route == selectedItemRoute
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(
                            id = if (selected) {
                                item.selectedIconId
                            } else item.iconId
                        ),
                        contentDescription = item.name
                    )
                }
            )
        }
    }
}

val bottomNavBarItems = listOf(
    BottomNavItem(
        name = "Home",
        route = "home",
        iconId = R.drawable.ic_home,
        selectedIconId = R.drawable.ic_selected_home
    ),
    BottomNavItem(
        name = "Chapters",
        route = "chapters",
        iconId = R.drawable.ic_chapters,
        selectedIconId = R.drawable.ic_selected_chapters
    ),
    BottomNavItem(
        name = "Stats",
        route = "stats",
        iconId = R.drawable.ic_stats,
        selectedIconId = R.drawable.ic_selected_stats
    ),
    BottomNavItem(
        name = "Profile",
        route = "profile",
        iconId = R.drawable.ic_profile,
        selectedIconId = R.drawable.ic_selected_profile
    ),
)