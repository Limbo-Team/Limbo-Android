package com.igorj.limboapp.model

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
    chapter: com.igorj.limboapp.model.Chapter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
) {
    val borderGradient =
        if (chapter.isCompleted) {
            com.igorj.limboapp.ui.theme.GreenGradient
        } else if (chapter.isUnlocked) {
            com.igorj.limboapp.ui.theme.OrangeGradient
        } else {
            com.igorj.limboapp.ui.theme.RedGradient
        }

    val progressBarColor =
        if (chapter.isCompleted) {
            com.igorj.limboapp.ui.theme.LightGreenGradient
        } else if (chapter.isUnlocked) {
            com.igorj.limboapp.ui.theme.BrightOrangeGradient
        } else {
            com.igorj.limboapp.ui.theme.RedGradient
        }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .clickable(
                enabled = chapter.isUnlocked,
                onClick = onClick
            )
            .background(com.igorj.limboapp.ui.theme.DarkBlackGradient)
            .border(
                width = if (chapter.isUnlocked) {
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
        com.igorj.limboapp.components.CircularProgressBar(
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
            color = com.igorj.limboapp.ui.theme.TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}