package com.igorj.quiz_presentation.playing_quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.core.BrightOrangeGradient
import com.igorj.core.components.LimboLogo
import com.igorj.core.util.UiEvent

@Composable
fun PlayingQuizScreen(
    onNavigation: () -> Unit,
    viewModel: PlayingScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state

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
            Box(modifier = Modifier.padding(it)) {

            }
        },
        bottomBar = {

        }
    )
}