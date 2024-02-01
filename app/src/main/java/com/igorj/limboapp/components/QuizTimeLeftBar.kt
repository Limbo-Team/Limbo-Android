package com.igorj.limboapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.R

@Composable
fun QuizTimeLeftBar(
    modifier: Modifier = Modifier,
    height: Dp = 36.dp,
    maxTime: Int = 60,
    remainingTime: Float
) {
    val timeLeft = (remainingTime / maxTime.toFloat())

    Box(
        modifier = modifier
            .width(240.dp)
            .height(height)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .width(240.dp * timeLeft)
                .height(height)
                .clip(RoundedCornerShape(25.dp))
                .background(OrangeGradient)
                .align(Alignment.CenterStart)
        )
        Icon(
            modifier = Modifier
                .padding(end = 6.dp)
                .align(Alignment.CenterEnd)
                .size(22.dp),
            painter = painterResource(id = R.drawable.ic_timer),
            tint = Color.Black,
            contentDescription = null
        )
    }
}