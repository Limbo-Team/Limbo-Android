package com.igorj.limboapp.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.limboapp.R
import com.igorj.limboapp.components.BestPersonItem
import com.igorj.limboapp.components.MiniChapter
import com.igorj.limboapp.ui.theme.LocalSpacing
import com.igorj.limboapp.util.UiEvent

@Composable
fun HomeScreen(
    onNavigation: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.loadBestPeople()
        viewModel.loadMiniChapters()
    }

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
        backgroundColor = MaterialTheme.colors.background,
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.best_in_group),
                        color = com.igorj.limboapp.ui.theme.TextWhite,
                        style = MaterialTheme.typography.h2,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
                        ) {
                            items(state.bestPeople) { person ->
                                BestPersonItem(
                                    name = person.name,
                                    imageUrl = person.imageUrl,
                                    flickers = person.flickers
                                )
                            }
                        }
                        if (state.isLoadingBestPeople) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(120.dp)
                                    .background(Color(0x77000000)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))

                    Text(
                        text = stringResource(id = R.string.study),
                        color = com.igorj.limboapp.ui.theme.TextWhite,
                        style = MaterialTheme.typography.h2,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyVerticalStaggeredGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopCenter),
                            columns = StaggeredGridCells.Fixed(2),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalItemSpacing = 16.dp,
                            content = {
                                items(state.miniChapters) { item ->
                                    MiniChapter(
                                        modifier = Modifier.fillMaxSize(),
                                        onClick = {},
                                        chapter = item
                                    )
                                }
                            }
                        )
                        if (state.isLoadingMiniChapters) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0x77000000)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        },
    )
}

