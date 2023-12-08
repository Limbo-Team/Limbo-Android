package com.igorj.quiz_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuizAnswersSection(
    modifier: Modifier = Modifier,
    answers: List<String>,
    selectedAnswerPosition: Int,
    onAnswerClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        repeat(answers.size) {
            QuizAnswerButton(
                answerText = answers[it],
                isSelected = selectedAnswerPosition == it,
                onClick = { onAnswerClick(it) },
                answerBackground = Color.Transparent
            )
        }
    }
}