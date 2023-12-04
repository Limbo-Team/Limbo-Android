package com.igorj.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.core.LocalSpacing
import com.igorj.core.OrangeGradient

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    gradient: Brush = OrangeGradient,
    textStyle: TextStyle = MaterialTheme.typography.button,
    isEnabled: Boolean = true,
    width: Float = 0.65f,
    fontSize: TextUnit = 16.sp
) {
    val spacing = LocalSpacing.current

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(100.dp),
        enabled = isEnabled,
        modifier = modifier,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxWidth(width)
                .padding(vertical = spacing.spaceMedium)
                .padding(horizontal = spacing.spaceLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = textStyle,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.align(Alignment.Center),
                fontSize = fontSize
            )
        }
    }
}