package com.igorj.limboapp.screen.finish_quiz

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.igorj.limboapp.ui.theme.DarkBackground
import com.igorj.limboapp.ui.theme.DarkVerticalQuizBackgroundGradient
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.TextWhite
import com.igorj.limboapp.components.GradientButton
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.components.QuizGainedPointsCircle
import com.igorj.limboapp.model.FinishedQuizResponse
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun FinishQuizScreen(
    finishedQuizResponseAsJson: String?,
    onNavigation: () -> Unit,
    viewModel: FinishQuizViewModel = hiltViewModel()
) {
    BackHandler {
        // do nothing
    }

    val state = viewModel.state

    val activity = LocalContext.current as? Activity

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
        viewModel.loadFinishedQuizResponse(finishedQuizResponseAsJson ?: "null")
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    onNavigation()
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkVerticalQuizBackgroundGradient),
        contentAlignment = Alignment.Center
    ) {
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
                if (state.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (state.finishedQuizResponse != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .padding(horizontal = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        QuizGainedPointsCircle(
                            gainedPoints = state.finishedQuizResponse.earnedPoints,
                            alternativeText = if (state.finishedQuizResponse.earnedPoints == 0) {
                                "${state.finishedQuizResponse.totalCorrectAnswers}/${state.finishedQuizResponse.totalQuestions}"
                            } else {
                                null
                            }
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = if (state.finishedQuizResponse.earnedPoints > 0) {
                                    R.string.you_are_amazing
                                } else {
                                    R.string.unfortunately_some_answers_were_incorrect
                                }),
                                color = TextWhite,
                                style = MaterialTheme.typography.h2,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = if (state.finishedQuizResponse.earnedPoints > 0) {
                                    R.string.congratulations_you_scored_required_points_in_this_chapter
                                } else {
                                    R.string.go_back_to_try_again
                                }),
                                color = TextWhite,
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Something went wrong, please try again later.",
                            color = TextWhite,
                            style = MaterialTheme.typography.h2,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
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
                        isEnabled = !state.isLoading,
                        text = stringResource(id = R.string.finish),
                        onClick = {
                            viewModel.onEvent(FinishQuizEvent.OnFinish)
                        }
                    )
                }
            }
        )
        if (!state.isLoading && (state.finishedQuizResponse?.earnedPoints ?: 0) > 0) {
            val party = Party(
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.95f,
                spread = 360,
                timeToLive = 3000L,
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                position = Position.Relative(0.5, 0.3),
                emitter = Emitter(duration = 150, TimeUnit.MILLISECONDS).max(150)
            )
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(party)
            )
        }
    }
}