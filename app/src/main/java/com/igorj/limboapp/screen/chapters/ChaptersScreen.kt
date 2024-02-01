package com.igorj.limboapp.screen.chapters

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
import com.igorj.limboapp.R
import com.igorj.limboapp.components.LimboLogo
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.model.BottomNavBar
import com.igorj.limboapp.model.ChapterCard
import com.igorj.limboapp.model.CircleImage
import com.igorj.limboapp.model.Flickers
import com.igorj.limboapp.model.bottomNavBarItems

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
                is com.igorj.limboapp.util.UiEvent.OnNavigate -> {
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
                com.igorj.limboapp.model.Flickers(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 26.dp),
                    onClick = {
                        viewModel.onEvent(ChaptersEvent.OnFlickersClick)
                    },
                    flickers = state.flickers
                )
                com.igorj.limboapp.components.LimboLogo(
                    modifier = Modifier.align(Alignment.Center)
                )
                com.igorj.limboapp.model.CircleImage(
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
                    com.igorj.limboapp.model.ChapterCard(
                        chapter = chapter,
                        onClick = {
                            onChapterNavigation(chapter.id)
                        }
                    )
                }
            }
        },
        bottomBar = {
            com.igorj.limboapp.model.BottomNavBar(
                items = com.igorj.limboapp.model.bottomNavBarItems,
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