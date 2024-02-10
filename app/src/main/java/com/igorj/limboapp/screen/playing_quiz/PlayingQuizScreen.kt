package com.igorj.limboapp.screen.playing_quiz

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.igorj.limboapp.MainViewModel
import com.igorj.limboapp.components.GradientButton
import com.igorj.limboapp.components.QuizAnswersSection
import com.igorj.limboapp.components.QuizNumberOfQuestionsLeft
import com.igorj.limboapp.components.QuizTimeLeftBar
import com.igorj.limboapp.ui.theme.BrightOrange
import com.igorj.limboapp.ui.theme.DarkVerticalQuizBackgroundGradient
import com.igorj.limboapp.ui.theme.TextWhite
import com.igorj.limboapp.util.UiEvent
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PlayingQuizScreen(
    quizId: String,
    onNavigation: () -> Unit,
    viewModel: PlayingScreenViewModel = hiltViewModel(),
    mainViewModel: MainViewModel
) {
    LaunchedEffect(key1 = true) {
        viewModel.loadQuestions(quizId)
    }

    val state = viewModel.state

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkVerticalQuizBackgroundGradient)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = BrightOrange
            )
        }
    } else if (state.questions.isNotEmpty()) {
        val currentQuestion = state.questions[state.currentQuestionIndex]
        val currentQuestionAnswers = remember(state.currentQuestionIndex) {
            buildList {
                addAll(currentQuestion.answers)
            }.shuffled()
        }

        val activity = LocalContext.current as? Activity
        SideEffect {
            activity?.window?.apply {
                WindowCompat.setDecorFitsSystemWindows(this, false)
                statusBarColor = Color.Transparent.toArgb()
                navigationBarColor = Color.Transparent.toArgb()
            }
        }

        BackHandler {
            viewModel.onEvent(PlayingQuizEvent.OnBackButtonClick)
        }

        LaunchedEffect(key1 = true) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.OnNavigate -> {
                        mainViewModel.updateTopBarInfo()
                        onNavigation()
                    }

                    else -> Unit
                }
            }
        }

        LaunchedEffect(key1 = state.timeLeft) {
            while (state.timeLeft > 0) {
                delay(100)
                viewModel.onEvent(PlayingQuizEvent.OnTimeTick(quizId))
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .background(DarkVerticalQuizBackgroundGradient)
        )
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = WindowInsets.systemBars
                        .asPaddingValues()
                        .calculateTopPadding(),
                    bottom = WindowInsets.systemBars
                        .asPaddingValues()
                        .calculateBottomPadding()
                ),
            backgroundColor = Color.Transparent,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        QuizTimeLeftBar(
                            maxTime = state.maxTime,
                            remainingTime = state.timeLeft
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        QuizNumberOfQuestionsLeft(
                            modifier = Modifier.weight(1f),
                            numOfQuestions = state.questions.size,
                            answeredQuestions = state.currentQuestionIndex + 1
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentQuestion.description ?: "",
                        modifier = Modifier.padding(top = 20.dp),
                        style = MaterialTheme.typography.h5,
                        fontSize = 20.sp,
                        color = TextWhite,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    QuizAnswersSection(
                        answers = currentQuestionAnswers,
                        selectedAnswerPosition = state.selectedAnswerPosition,
                        onAnswerClick = { answerPosition ->
                            viewModel.onEvent(PlayingQuizEvent.OnAnswerClick(answerPosition))
                        }
                    )
                }
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp, top = 20.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    GradientButton(
                        text = "Next",
                        onClick = {
                            if (state.currentQuestionIndex + 1 >= state.questions.size) {
                                val lastAnswer = currentQuestionAnswers[state.selectedAnswerPosition]
                                viewModel.onEvent(PlayingQuizEvent.OnFinish(lastAnswer, quizId))
                            } else {
                                viewModel.onEvent(
                                    PlayingQuizEvent.OnNextQuestionClick(
                                        if (state.selectedAnswerPosition == -1) {
                                            ""
                                        } else {
                                            currentQuestionAnswers[state.selectedAnswerPosition]
                                        }
                                    )
                                )
                            }
                        },
                        isEnabled = state.selectedAnswerPosition != -1
                    )
                }
            }
        )
    } else {

    }
}