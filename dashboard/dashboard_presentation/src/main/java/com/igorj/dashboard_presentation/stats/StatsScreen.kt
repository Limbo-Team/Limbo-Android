package com.igorj.dashboard_presentation.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.core.LocalSpacing
import com.igorj.core.R
import com.igorj.core.TextWhite
import com.igorj.core.components.LimboLogo
import com.igorj.core.util.UiEvent
import com.igorj.dashboard_presentation.components.BottomNavBar
import com.igorj.dashboard_presentation.components.CircleImage
import com.igorj.dashboard_presentation.components.Flickers
import com.igorj.dashboard_presentation.components.bottomNavBarItems
import com.igorj.dashboard_presentation.stats.components.MiniStats

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StatsScreen(
    scaffoldState: ScaffoldState,
    onNavigation: (String) -> Unit,
    viewModel: StatsViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    onNavigation(viewModel.state.selectedScreen)
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context),
                        actionLabel = "OK"
                    )
                    keyboardController?.hide()
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
                        viewModel.onEvent(StatsEvent.OnFlickersClick)
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
                    onClick = {
                        viewModel.onEvent(StatsEvent.OnProfileClick)
                    },
                    size = 40.dp
                )
            }
        },
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
                        text = stringResource(id = R.string.your_statistics),
                        color = TextWhite,
                        style = MaterialTheme.typography.h2,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxWidth(),
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalItemSpacing = 16.dp,
                        content = {
                            item {
                                MiniStats(
                                    text = stringResource(id = R.string.bonus_exam_points),
                                    value = "+${state.userStats.examBonus}",
                                    onClick = {
                                        viewModel.onEvent(StatsEvent.OnExamBonusClick)
                                    },
                                    valueSize = 36.sp
                                )
                            }
                            item {
                                MiniStats(
                                    text = stringResource(id = R.string.bonus_dante_points),
                                    value = "+${state.userStats.danteBonus}",
                                    onClick = {
                                        viewModel.onEvent(StatsEvent.OnDanteBonusClick)
                                    },
                                    valueSize = 36.sp
                                )
                            }
                            item {
                                MiniStats(
                                    text = stringResource(id = R.string.total_questions_answered),
                                    value = state.userStats.questionSolved.toString(),
                                    onClick = {
                                        viewModel.onEvent(StatsEvent.OnQuestionsSolvedClick)
                                    }
                                )
                            }
                            item {
                                MiniStats(
                                    text = stringResource(id = R.string.finished_chapters),
                                    value = state.userStats.chaptersFinished.toString(),
                                    onClick = {
                                        viewModel.onEvent(StatsEvent.OnChaptersFinishedClick)
                                    }
                                )
                            }
                            item {
                                MiniStats(
                                    text = stringResource(id = R.string.average_answer_time),
                                    value = "${state.userStats.averageAnswerTime}s",
                                    onClick = {
                                        viewModel.onEvent(StatsEvent.OnAverageAnswerTimeClick)
                                    }
                                )
                            }
                            item {
                                MiniStats(
                                    text = stringResource(id = R.string.most_questions_answered_in_day),
                                    value = state.userStats.mostAnswersInDay.toString(),
                                    onClick = {
                                        viewModel.onEvent(StatsEvent.OnMostAnswersInDayClick)
                                    }
                                )
                            }
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
                        StatsEvent.OnBottomNavBarClick(it.route)
                    )
                }
            )
        }
    )
}