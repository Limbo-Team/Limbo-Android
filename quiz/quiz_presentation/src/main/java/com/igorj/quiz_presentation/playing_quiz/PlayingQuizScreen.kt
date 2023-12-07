package com.igorj.quiz_presentation.playing_quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.igorj.core.BrightOrangeGradient
import com.igorj.core.R
import com.igorj.core.TextWhite
import com.igorj.core.components.GradientButton
import com.igorj.core.components.LimboLogo
import com.igorj.core.util.UiEvent
import com.igorj.quiz_presentation.components.QuizAnswersSection

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

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BrightOrangeGradient)
    )
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            ) {
                LimboLogo(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        .fillMaxWidth(0.8f)
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
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                GradientButton(
                    text = "Next",
                    onClick = {
                        viewModel.onEvent(PlayingQuizEvent.OnNextQuestion)
                    }
                )
            }
        }
    )
}