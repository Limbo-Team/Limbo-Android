package com.igorj.limboapp.screen.profile

import androidx.compose.foundation.background
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
import com.igorj.limboapp.R
import com.igorj.limboapp.components.BottomNavBar
import com.igorj.limboapp.components.CustomTextField
import com.igorj.limboapp.components.Flickers
import com.igorj.limboapp.components.GradientButton
import com.igorj.limboapp.components.LogoutButton
import com.igorj.limboapp.components.ProfileInfo
import com.igorj.limboapp.components.RedeemFlickersCard
import com.igorj.limboapp.components.bottomNavBarItems
import com.igorj.limboapp.ui.theme.OrangeGradient
import com.igorj.limboapp.ui.theme.TextWhite
import com.igorj.limboapp.util.UiEvent

@Composable
fun ProfileScreen(
    onNavigation: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.loadProfileInfo()
    }

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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        ProfileInfo(
            image = state.user.image,
            firstName = state.user.firstName,
            lastName = state.user.lastName,
            email = state.user.email
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.change_password),
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
                hint = stringResource(id = R.string.old_password),
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
                hint = stringResource(id = R.string.new_password),
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
                hint = stringResource(id = R.string.repeat_new_password),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            GradientButton(
                text = stringResource(id = R.string.change_password),
                gradient = OrangeGradient,
                width = 0.6f,
                fontSize = 14.sp,
                onClick = {
                    viewModel.onEvent(ProfileEvent.OnChangePasswordClick)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            RedeemFlickersCard(
                isLoading = state.isLoading,
                points = state.user.points,
                onClick = {
                    viewModel.onEvent(ProfileEvent.OnExchangeFlickersClick)
                }
            )
        }
    }
}