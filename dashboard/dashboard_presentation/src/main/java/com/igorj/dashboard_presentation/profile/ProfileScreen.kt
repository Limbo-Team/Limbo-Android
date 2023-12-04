package com.igorj.dashboard_presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.core.OrangeGradient
import com.igorj.core.R
import com.igorj.core.TextWhite
import com.igorj.core.components.CustomTextField
import com.igorj.core.components.GradientButton
import com.igorj.core.components.LimboLogo
import com.igorj.core.util.UiEvent
import com.igorj.dashboard_presentation.components.BottomNavBar
import com.igorj.dashboard_presentation.components.CircleImage
import com.igorj.dashboard_presentation.components.Flickers
import com.igorj.dashboard_presentation.components.ProfileInfo
import com.igorj.dashboard_presentation.components.RedeemFlickersCard
import com.igorj.dashboard_presentation.components.bottomNavBarItems

@Composable
fun ProfileScreen(
    onNavigation: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    onNavigation(viewModel.state.selectedScreen)
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            ) {
                Flickers(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 26.dp),
                    onClick = {
                        viewModel.onEvent(ProfileEvent.OnFlickersClick)
                    },
                    flickers = state.flickers
                )
                LimboLogo(
                    modifier = Modifier.align(Alignment.Center)
                )
                CircleImage(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 30.dp),
                    imageUrl = state.user.imageUrl,
                    contentDescription = stringResource(id = R.string.profile),
                    size = 40.dp
                )
            }
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ProfileInfo(
                    imageUrl = state.user.imageUrl,
                    name = state.user.name,
                    email = state.user.email
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Zmień hasło",
                        color = TextWhite,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 10.dp)
                    )
                    CustomTextField(
                        value = state.oldPassword,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.OnOldPasswordChange(it))
                        },
                        hint = "Stare hasło",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ) {

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomTextField(
                        value = state.newPassword,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.OnNewPasswordChange(it))
                        },
                        hint = "Nowe hasło",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ) {

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomTextField(
                        value = state.newPasswordConfirmation,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.OnNewPasswordConfirmationChange(it))
                        },
                        hint = "Powtórz nowe hasło",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ) {

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    GradientButton(
                        text = "Zmień hasło",
                        gradient = OrangeGradient,
                        width = 0.6f,
                        fontSize = 14.sp,
                        onClick = {
                            viewModel.onEvent(ProfileEvent.OnChangePasswordClick)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    RedeemFlickersCard()
                }
            }
        },
        bottomBar = {
            BottomNavBar(
                items = bottomNavBarItems,
                selectedItemRoute = state.selectedScreen,
                onItemClick = {
                    viewModel.onEvent(
                        ProfileEvent.OnBottomNavBarClick(it.route)
                    )
                }
            )
        }
    )
}