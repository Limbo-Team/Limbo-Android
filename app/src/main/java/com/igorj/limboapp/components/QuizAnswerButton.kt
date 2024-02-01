package com.igorj.limboapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun QuizAnswerButton(
    modifier: Modifier = Modifier,
    answerText: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    answerBackground: Color = Color.Transparent,
) {
    val answerIcon = if (isSelected) {
        R.drawable.ic_answer_selected
    } else R.drawable.ic_answer_not_selected

    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(25.dp))
            .background(answerBackground)
            .border(
                width = 1.dp,
                brush = OrangeGradient,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 6.dp),
            text = answerText,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
        Image(
            painter = painterResource(id = answerIcon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(20.dp)
                .align(Alignment.CenterEnd)
        )
    }
}