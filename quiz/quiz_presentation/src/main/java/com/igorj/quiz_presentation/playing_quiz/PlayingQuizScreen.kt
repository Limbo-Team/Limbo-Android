package com.igorj.quiz_presentation.playing_quiz

import android.app.Activity
import androidx.compose.foundation.Image
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.igorj.core.DarkBackground
import com.igorj.core.DarkVerticalQuizBackgroundGradient
import com.igorj.core.R
import com.igorj.core.TextWhite
import com.igorj.core.components.GradientButton
import com.igorj.core.components.LimboLogo
import com.igorj.core.util.UiEvent
import com.igorj.quiz_presentation.components.QuizAnswersSection
import com.igorj.quiz_presentation.components.QuizNumberOfQuestionsLeft
import com.igorj.quiz_presentation.components.QuizTimeLeftBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PlayingQuizScreen(
    onNavigation: () -> Unit,
    viewModel: PlayingScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val currentQuestion = state.questions[state.currentQuestionIndex]
    val currentQuestionAnswers = remember(state.currentQuestionIndex) {
        buildList {
            addAll(currentQuestion.wrongAnswers)
            add(currentQuestion.correctAnswer)
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

    DisposableEffect(Unit) {
        onDispose {
            activity?.window?.apply {
                WindowCompat.setDecorFitsSystemWindows(this, true)
                statusBarColor = DarkBackground.toArgb()
                navigationBarColor = DarkBackground.toArgb()
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    onNavigation()
                }

                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = state.timeLeft) {
        while (state.timeLeft > 0) {
            delay(100)
            viewModel.onEvent(PlayingQuizEvent.OnTimeTick)
        }
        if (state.timeLeft == 0f) {
            viewModel.onEvent(PlayingQuizEvent.OnFinish)
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
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 8.dp)
            ) {
                LimboLogo(
                    modifier = Modifier.align(Alignment.Center),
                    textColor = TextWhite
                )
            }
        },
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
                    QuizTimeLeftBar(remainingTime = state.timeLeft)
                    Spacer(modifier = Modifier.width(30.dp))
                    QuizNumberOfQuestionsLeft(
                        modifier = Modifier.weight(1f),
                        numOfQuestions = state.questions.size,
                        answeredQuestions = state.currentQuestionIndex + 1
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = currentQuestion.text,
                    modifier = Modifier.padding(top = 20.dp),
                    style = MaterialTheme.typography.h5,
                    fontSize = 20.sp,
                    color = TextWhite,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    painter = rememberImagePainter(
                        data = state.questions[state.currentQuestionIndex].imageUrl,
                        builder = {
                            crossfade(true)
                            error(R.drawable.ic_profile)
                            fallback(R.drawable.ic_profile)
                        }
                    ),
                    contentDescription = "Question image",
                    contentScale = ContentScale.Crop
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
                        viewModel.onEvent(PlayingQuizEvent.OnNextQuestionClick(
                            if (state.selectedAnswerPosition == -1) {
                                ""
                            } else {
                                currentQuestionAnswers[state.selectedAnswerPosition]
                            }
                        ))
                    },
                    isEnabled = state.selectedAnswerPosition != -1
                )
            }
        }
    )
}