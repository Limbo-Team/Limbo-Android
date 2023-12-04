package com.igorj.auth_presentation.forgot_password.enter_verification_code

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.core.BrightOrange
import com.igorj.core.LightGray
import com.igorj.core.LocalSpacing
import com.igorj.core.R
import com.igorj.core.TextWhite
import com.igorj.core.components.CustomTextField
import com.igorj.core.components.GradientButton
import com.igorj.core.util.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerificationCodeScreen(
    scaffoldState: ScaffoldState,
    onNavigation: () -> Unit,
    viewModel: VerificationCodeViewModel = hiltViewModel()
) {
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

                else -> Unit
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.limbo),
                fontSize = 36.sp,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold,
                color = BrightOrange,
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.verification_code),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.please_enter_verification_code),
                style = MaterialTheme.typography.body1,
                color = LightGray,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(26.dp))
            CustomTextField(
                value = state.verificationCode,
                onValueChange = {
                    viewModel.onEvent(VerificationCodeEvent.OnVerificationCodeChange(it))
                },
                hint = stringResource(id = R.string.verification_code),
            )
        }

        GradientButton(
            text = stringResource(id = R.string.check_verification_code),
            fontSize = 15.sp,
            width = 0.8f,
            onClick = { viewModel.onEvent(VerificationCodeEvent.OnButtonClick) }
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