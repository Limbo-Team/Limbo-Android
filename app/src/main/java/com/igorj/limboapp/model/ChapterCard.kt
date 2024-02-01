package com.igorj.limboapp.model

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.BrightOrangeGradient
import com.igorj.limboapp.ui.theme.DarkBlackGradient
import com.igorj.limboapp.ui.theme.GreenGradient
import com.igorj.limboapp.ui.theme.LightGreen
import com.igorj.limboapp.ui.theme.LightGreenGradient
import com.igorj.limboapp.ui.theme.LightOrange
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.ui.theme.RedGradient
import com.igorj.limboapp.ui.theme.TextWhite
import com.igorj.limboapp.components.CircularProgressBar
import com.igorj.limboapp.model.Chapter

@Composable
fun ChapterCard(
    modifier: Modifier = Modifier,
    chapter: com.igorj.limboapp.model.Chapter,
    onClick: () -> Unit = {},
    borderWidth: Dp = 2.dp
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

    val pointsColor = if (!chapter.isUnlocked || !chapter.isCompleted) {
        com.igorj.limboapp.ui.theme.LightOrange
    } else com.igorj.limboapp.ui.theme.LightGreen

    Row(
        modifier = modifier
            .alpha(
                if (chapter.isUnlocked) 1f else 0.5f
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = borderWidth,
                brush = borderGradient,
                shape = RoundedCornerShape(20.dp)
            )
            .background(com.igorj.limboapp.ui.theme.DarkBlackGradient)
            .clickable(
                enabled = chapter.isUnlocked,
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = chapter.title,
                color = com.igorj.limboapp.ui.theme.TextWhite,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            Text(
                text = "Zdobyte punkty",
                color = com.igorj.limboapp.ui.theme.TextWhite,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(bottom = 6.dp)
            )
            Text(
                text = "${chapter.gainedPoints}/${chapter.maxPoints}",
                color = pointsColor,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        com.igorj.limboapp.components.CircularProgressBar(
            modifier = Modifier.weight(1f),
            percentage = (chapter.gainedPoints / chapter.maxPoints.toFloat()),
            number = 100,
            progressGradient = progressBarColor
        )
    }
}