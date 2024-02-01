package com.igorj.limboapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BestPersonItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    flickers: Int,
    flickersImageSize: Dp = 18.dp,
    flickersTextSize: TextUnit = 13.sp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircleImage(
                imageUrl = imageUrl,
                contentDescription = name,
                size = 80.dp,
                modifier = Modifier.padding(6.dp)
            )
            Flickers(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = {},
                isEnabled = false,
                flickers = flickers,
                imageSize = flickersImageSize,
                textSize = flickersTextSize
            )
        }
        Text(
            text = name,
            color = com.igorj.limboapp.ui.theme.TextWhite,
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.paddingFromBaseline(top = 16.dp)
        )
    }
}