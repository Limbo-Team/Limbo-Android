package com.igorj.limboapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.BrightOrange
import com.igorj.limboapp.R

@Composable
fun LimboLogo(
    modifier: Modifier = Modifier,
    textColor: Color = com.igorj.limboapp.ui.theme.BrightOrange
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_flame),
            contentDescription = stringResource(id = R.string.limbo),
            modifier = Modifier
                .size(48.dp)
        )
        Text(
            text = stringResource(id = R.string.limbo),
            fontSize = 36.sp,
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}