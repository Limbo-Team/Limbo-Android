package com.igorj.limboapp.screen.quizzes

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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.igorj.limboapp.R
import com.igorj.limboapp.components.BottomNavBar
import com.igorj.limboapp.components.CircleImage
import com.igorj.limboapp.components.Flickers
import com.igorj.limboapp.components.LimboLogo
import com.igorj.limboapp.components.QuizCard
import com.igorj.limboapp.components.bottomNavBarItems
import com.igorj.limboapp.util.UiEvent

@Composable
fun QuizzesScreen(
    chapterId: String,
    onNavigation: (String) -> Unit,
    onQuizClick: (String) -> Unit,
    viewModel: QuizzesViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.loadQuizzes(chapterId = chapterId)
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
                items(state.quizzes) { quiz ->
                    QuizCard(
                        quiz = quiz,
                        onClick = {
                            onQuizClick(quiz.quizId)
                        }
                    )
                }
            }
        },
    )
}