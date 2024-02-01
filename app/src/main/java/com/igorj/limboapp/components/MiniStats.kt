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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.BrightOrangeGradient
import com.igorj.limboapp.ui.theme.DarkBlackGradient
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun MiniStats(
    text: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    borderGradient: Brush = BrightOrangeGradient,
    textSize: TextUnit = 16.sp,
    valueSize: TextUnit = 30.sp
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .clickable { onClick() }
            .background(DarkBlackGradient)
            .border(
                width = borderWidth,
                brush = borderGradient,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(vertical = 14.dp)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            fontSize = valueSize,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontSize = textSize,
            textAlign = TextAlign.Center
        )
    }
}