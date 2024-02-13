package com.igorj.limboapp.screen.forgot_password.change_password

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.limboapp.R
import com.igorj.limboapp.components.CustomTextField
import com.igorj.limboapp.components.GradientButton
import com.igorj.limboapp.ui.theme.LocalSpacing
import com.igorj.limboapp.util.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChangePasswordScreen(
    scaffoldState: ScaffoldState,
    onNavigation: () -> Unit,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    BackHandler {
        // do nothing
    }

    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context),
                        actionLabel = "OK"
                    )
                    keyboardController?.hide()
                }
                is UiEvent.OnNavigate -> {
                    onNavigation()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
            .padding(bottom = spacing.spaceLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
                .padding(bottom = spacing.spaceLarge)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.change_password),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold,
                color = com.igorj.limboapp.ui.theme.TextWhite,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(26.dp))
            CustomTextField(
                value = state.newPassword,
                onValueChange = {
                    viewModel.onEvent(ChangePasswordEvent.OnNewPasswordChange(it))
                },
                hint = stringResource(id = R.string.new_password)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = state.confirmNewPassword,
                onValueChange = {
                    viewModel.onEvent(ChangePasswordEvent.OnConfirmNewPasswordChange(it))
                },
                hint = stringResource(id = R.string.repeat_new_password)
            )
        }

        GradientButton(
            text = stringResource(id = R.string.confirm),
            fontSize = 15.sp,
            width = 0.6f,
            onClick = { viewModel.onEvent(ChangePasswordEvent.OnButtonClick) }
        )
    }

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x52000000)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}