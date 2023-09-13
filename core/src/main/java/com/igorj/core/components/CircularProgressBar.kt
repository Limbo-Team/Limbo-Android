package com.igorj.core.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.core.CircleGray
import com.igorj.core.OrangeGradient
import com.igorj.core.TextWhite

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    number: Int = 100,
    radius: Dp = 35.dp,
    progressGradient: Brush = OrangeGradient,
    innerCircleColor: Color = CircleGray,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    initValue: Float = 0f,
    isUnlocked: Boolean = true
) {
    var hasAnimationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (hasAnimationPlayed) {
            percentage
        } else initValue,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )
    LaunchedEffect(key1 = true) {
        hasAnimationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(radius * 2f + strokeWidth)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
        ) {
            drawCircle(
                color = innerCircleColor
            )
            drawArc(
                brush = progressGradient,
                startAngle = -90f,
                sweepAngle = if (isUnlocked) 360f * curPercentage.value else 360f,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val percentageTextSize = 18.sp * (radius / 35.dp)
            Text(
                text = (curPercentage.value * number).toInt().toString() + "%",
                color = TextWhite,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                fontSize = percentageTextSize
            )
        }
    }
}