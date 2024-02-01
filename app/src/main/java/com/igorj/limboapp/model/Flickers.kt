package com.igorj.limboapp.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.DarkGray
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun Flickers(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    flickers: Int,
    imageSize: Dp = 20.dp,
    textSize: TextUnit = 15.sp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(com.igorj.limboapp.ui.theme.DarkGray)
            .clickable(
                enabled = isEnabled,
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_flame),
            contentDescription = stringResource(id = R.string.flickers),
            modifier = Modifier
                .padding(6.dp)
                .size(imageSize)
        )
        Text(
            text = flickers.toString(),
            color = com.igorj.limboapp.ui.theme.TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = textSize,
            modifier = Modifier
                .padding(end = 10.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}