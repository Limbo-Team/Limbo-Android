package com.igorj.limboapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.igorj.limboapp.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String?,
    onClick: () -> Unit = {},
    size: Dp
) {
    Image(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .clickable { onClick() },
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                error(R.drawable.ic_profile)
                fallback(R.drawable.ic_profile)
            }
        ),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}