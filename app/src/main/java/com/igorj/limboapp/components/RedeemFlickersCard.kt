package com.igorj.limboapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.BlackGradient
import com.igorj.limboapp.ui.theme.BrightOrange
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun RedeemFlickersCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(horizontal = 26.dp)
            .clip(RoundedCornerShape(26.dp))
            .clickable { onClick() }
            .border(
                width = 1.5.dp,
                brush = OrangeGradient,
                shape = RoundedCornerShape(26.dp)
            )
            .background(BlackGradient)
            .padding(start = 26.dp, end = 16.dp, top = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = "Płomyki do wymiany",
                color = TextWhite,
                fontSize = 16.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "49",
                color = BrightOrange,
                fontSize = 30.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Kliknij aby przejeść do ekranu wymiany płomyków.",
                color = TextWhite,
                fontSize = 12.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_redeem),
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
    }
}