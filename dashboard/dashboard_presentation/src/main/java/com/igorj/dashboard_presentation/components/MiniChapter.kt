package com.igorj.dashboard_presentation.components

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
import com.igorj.core.BrightOrangeGradient
import com.igorj.core.DarkBlackGradient
import com.igorj.core.GreenGradient
import com.igorj.core.LightGreenGradient
import com.igorj.core.OrangeGradient
import com.igorj.core.RedGradient
import com.igorj.core.TextWhite
import com.igorj.core.components.CircularProgressBar
import com.igorj.dashboard_domain.model.Chapter

@Composable
fun MiniChapter(
    chapter: Chapter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
) {
    val borderGradient =
        if (chapter.isCompleted) {
            GreenGradient
        } else if (chapter.isUnlocked) {
            OrangeGradient
        } else {
            RedGradient
        }

    val progressBarColor =
        if (chapter.isCompleted) {
            LightGreenGradient
        } else if (chapter.isUnlocked) {
            BrightOrangeGradient
        } else {
            RedGradient
        }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .clickable(
                enabled = chapter.isUnlocked,
                onClick = onClick
            )
            .background(DarkBlackGradient)
            .border(
                width = borderWidth,
                brush = borderGradient,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(top = 14.dp, bottom = 10.dp)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressBar(
            percentage = (chapter.gainedPoints / chapter.maxPoints.toFloat()),
            radius = 30.dp,
            progressGradient = progressBarColor,
            strokeWidth = 5.5.dp,
            isUnlocked = chapter.isUnlocked
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 6.dp),
            text = chapter.title,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}