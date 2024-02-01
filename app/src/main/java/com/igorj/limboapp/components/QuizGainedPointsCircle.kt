package com.igorj.limboapp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.BrightOrangeGradient
import com.igorj.limboapp.ui.theme.CircleGray

@Composable
fun QuizGainedPointsCircle(
    modifier: Modifier = Modifier,
    gainedPoints: Int = 1
) {
    Box(modifier = modifier.size(220.5.dp)) {
        CircularGainedPointsIndicator(
            modifier = Modifier.align(Alignment.BottomCenter),
            percentage = 1f,
            radius = 95.dp,
            innerCircleColor = Color.Transparent,
            strokeWidth = 15.dp,
            gainedFlickers = gainedPoints
        )
        Image(
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.ic_test_done_check_marks),
            contentDescription = null
        )
    }
}

@Composable
fun CircularGainedPointsIndicator(
    modifier: Modifier = Modifier,
    percentage: Float,
    radius: Dp = 35.dp,
    progressGradient: Brush = BrightOrangeGradient,
    innerCircleColor: Color = CircleGray,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    initValue: Float = 0f,
    gainedFlickers: Int = 1
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else initValue,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ), label = ""
    )
    val curGainedPoints = animateIntAsState(
        targetValue = if (animationPlayed) gainedFlickers else 0,
        animationSpec = tween(
            durationMillis = animDuration - 250,
            delayMillis = animDelay
        ), label = ""
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
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
                sweepAngle = 360f * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val gainedFlickersTextSize = 18.sp * (radius / 30.dp)
            Text(
                text = "+${curGainedPoints.value}",
                color = com.igorj.limboapp.ui.theme.TextWhite,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                fontSize = gainedFlickersTextSize,
            )
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = null
            )
        }
    }
}