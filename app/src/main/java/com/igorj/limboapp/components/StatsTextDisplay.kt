package com.igorj.limboapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.BlackGradient
import com.igorj.limboapp.ui.theme.BrightOrangeGradient
import com.igorj.limboapp.ui.theme.LocalSpacing
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun StatsTextDisplay(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    value: String,
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .clickable { onClick() }
            .background(BlackGradient)
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                brush = BrightOrangeGradient,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = spacing.spaceSmall)
            .padding(vertical = spacing.spaceExtraSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = text,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = value,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}