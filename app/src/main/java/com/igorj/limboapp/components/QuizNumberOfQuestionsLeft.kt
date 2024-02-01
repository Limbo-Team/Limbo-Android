package com.igorj.limboapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun QuizNumberOfQuestionsLeft(
    modifier: Modifier = Modifier,
    height: Dp = 36.dp,
    numOfQuestions: Int,
    answeredQuestions: Int
) {
    val questionsLeft = "$answeredQuestions/$numOfQuestions"
    Box(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(25.dp))
            .background(com.igorj.limboapp.ui.theme.OrangeGradient)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = questionsLeft,
            color = com.igorj.limboapp.ui.theme.TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}