package com.igorj.limboapp.screen.profile

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
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.R
import com.igorj.limboapp.ui.theme.TextWhite
import com.igorj.limboapp.components.CustomTextField
import com.igorj.limboapp.components.GradientButton
import com.igorj.limboapp.components.LimboLogo
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.model.BottomNavBar
import com.igorj.limboapp.model.Flickers
import com.igorj.limboapp.model.LogoutButton
import com.igorj.limboapp.model.ProfileInfo
import com.igorj.limboapp.model.RedeemFlickersCard
import com.igorj.limboapp.model.bottomNavBarItems

@Composable
fun ProfileScreen(
    onNavigation: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is com.igorj.limboapp.util.UiEvent.OnNavigate -> {
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
                com.igorj.limboapp.model.Flickers(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 26.dp),
                    onClick = {
                        viewModel.onEvent(ProfileEvent.OnFlickersClick)
                    },
                    flickers = state.flickers
                )
                com.igorj.limboapp.components.LimboLogo(
                    modifier = Modifier.align(Alignment.Center)
                )
                com.igorj.limboapp.model.LogoutButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 26.dp),
                    onClick = {
                        viewModel.onEvent(ProfileEvent.OnLogoutClick)
                    }
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
                com.igorj.limboapp.model.ProfileInfo(
                    imageUrl = state.user.imageUrl,
                    name = state.user.name,
                    email = state.user.email
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.change_password),
                        color = com.igorj.limboapp.ui.theme.TextWhite,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 10.dp)
                    )
                    com.igorj.limboapp.components.CustomTextField(
                        value = state.oldPassword,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.OnOldPasswordChange(it))
                        },
                        hint = stringResource(id = R.string.old_password),
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ) {

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    com.igorj.limboapp.components.CustomTextField(
                        value = state.newPassword,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.OnNewPasswordChange(it))
                        },
                        hint = stringResource(id = R.string.new_password),
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ) {

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    com.igorj.limboapp.components.CustomTextField(
                        value = state.newPasswordConfirmation,
                        onValueChange = {
                            viewModel.onEvent(ProfileEvent.OnNewPasswordConfirmationChange(it))
                        },
                        hint = stringResource(id = R.string.repeat_new_password),
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ) {

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    com.igorj.limboapp.components.GradientButton(
                        text = stringResource(id = R.string.change_password),
                        gradient = com.igorj.limboapp.ui.theme.OrangeGradient,
                        width = 0.6f,
                        fontSize = 14.sp,
                        onClick = {
                            viewModel.onEvent(ProfileEvent.OnChangePasswordClick)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    com.igorj.limboapp.model.RedeemFlickersCard()
                }
            }
        },
        bottomBar = {
            com.igorj.limboapp.model.BottomNavBar(
                items = com.igorj.limboapp.model.bottomNavBarItems,
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