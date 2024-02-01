package com.igorj.limboapp.screen.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.BlackGradient
import com.igorj.limboapp.components.GradientButton
import com.igorj.limboapp.ui.theme.LocalSpacing

@Composable
fun WelcomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg_fire),
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier
                .padding(top = screenHeight * 0.15f)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.limbo),
                fontSize = 90.sp,
                color = com.igorj.limboapp.ui.theme.TextWhite,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(id = R.string.welcome_app_desc),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = 22.sp,
                color = com.igorj.limboapp.ui.theme.TextWhite,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = (-10).dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = spacing.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GradientButton(
                text = stringResource(id = R.string.sign_in),
                onClick = { onNavigateToLogin() }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            GradientButton(
                text = stringResource(id = R.string.sign_up),
                onClick = { onNavigateToRegister() },
                gradient = BlackGradient
            )
        }
    }
}