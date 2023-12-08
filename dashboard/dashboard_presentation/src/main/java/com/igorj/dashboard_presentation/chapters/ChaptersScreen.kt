package com.igorj.dashboard_presentation.chapters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.core.R
import com.igorj.core.components.LimboLogo
import com.igorj.core.util.UiEvent
import com.igorj.dashboard_presentation.components.BottomNavBar
import com.igorj.dashboard_presentation.components.ChapterCard
import com.igorj.dashboard_presentation.components.CircleImage
import com.igorj.dashboard_presentation.components.Flickers
import com.igorj.dashboard_presentation.components.bottomNavBarItems

@Composable
fun ChaptersScreen(
    onNavigation: (String) -> Unit,
    onChapterNavigation: (Int) -> Unit,
    viewModel: ChaptersViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    onNavigation(viewModel.state.selectedScreen)
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            ) {
                Flickers(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 26.dp),
                    onClick = {
                        viewModel.onEvent(ChaptersEvent.OnFlickersClick)
                    },
                    flickers = state.flickers
                )
                LimboLogo(
                    modifier = Modifier.align(Alignment.Center)
                )
                CircleImage(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 30.dp),
                    imageUrl = "https://i.imgur.com/36nMXsk.jpg",
                    contentDescription = stringResource(id = R.string.profile),
                    size = 40.dp
                )
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(
                        horizontal = 16.dp
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    bottom = 16.dp
                )
            ) {
                items(state.chapters) { chapter ->
                    ChapterCard(
                        chapter = chapter,
                        onClick = {
                            onChapterNavigation(chapter.id)
                        }
                    )
                }
            }
        },
        bottomBar = {
            BottomNavBar(
                items = bottomNavBarItems,
                selectedItemRoute = state.selectedScreen,
                onItemClick = {
                    viewModel.onEvent(
                        ChaptersEvent.OnBottomNavBarClick(it.route)
                    )
                }
            )
        }
    )
}