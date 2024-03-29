package com.igorj.limboapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.BrightOrangeGradient
import com.igorj.limboapp.ui.theme.DarkBlackGradient
import com.igorj.limboapp.ui.theme.GreenGradient
import com.igorj.limboapp.ui.theme.LightGreenGradient
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.ui.theme.RedGradient
import com.igorj.limboapp.ui.theme.TextWhite
import com.igorj.limboapp.components.CircularProgressBar
import com.igorj.limboapp.model.Chapter

@Composable
fun MiniChapter(
    chapter: Chapter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
) {
    val isCompleted = chapter.doneQuizzes == chapter.maximumQuizzes
    val isStarted = chapter.doneQuizzes > 0

    val borderGradient =
        if (isCompleted) {
            GreenGradient
        } else if (isStarted) {
            OrangeGradient
        } else {
            RedGradient
        }

    val progressBarColor =
        if (isCompleted) {
            LightGreenGradient
        } else if (isStarted) {
            BrightOrangeGradient
        } else {
            RedGradient
        }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .background(DarkBlackGradient)
            .border(
                width = if (isStarted) {
                    borderWidth
                } else 1.dp,
                brush = borderGradient,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(top = 14.dp, bottom = 10.dp)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressBar(
            percentage = (chapter.percentage / 100.toFloat()),
            radius = 30.dp,
            progressGradient = progressBarColor,
            strokeWidth = 5.5.dp,
            isUnlocked = isStarted
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 6.dp),
            text = chapter.chapterTitle,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}