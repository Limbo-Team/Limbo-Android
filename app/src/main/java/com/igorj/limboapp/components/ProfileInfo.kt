package com.igorj.limboapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.components.CircleImage
import com.igorj.limboapp.ui.theme.TextWhite

@Composable
fun ProfileInfo(
    modifier: Modifier = Modifier,
    image: String,
    firstName: String,
    lastName: String,
    email: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CircleImage(
            imageUrl = image,
            contentDescription = "Profile picture",
            size = 100.dp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = firstName,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Text(
            text = lastName,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Text(
            text = email,
            color = TextWhite,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
    }
}