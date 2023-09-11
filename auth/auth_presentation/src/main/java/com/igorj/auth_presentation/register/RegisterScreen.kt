package com.igorj.auth_presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.igorj.auth_presentation.components.CustomTextField
import com.igorj.auth_presentation.register.model.RegisterUserInfo
import com.igorj.core.R
import com.igorj.core.util.UiEvent
import com.igorj.core_ui.BrightOrange
import com.igorj.core_ui.LocalSpacing
import com.igorj.core_ui.TextWhite
import com.igorj.core_ui.components.GradientButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    scaffoldState: ScaffoldState,
    onNavigation: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
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
                        message = event.message.asString(context)
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
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(26.dp))
            CustomTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.OnEmailChange(it))
                },
                hint = stringResource(id = R.string.email),
                trailingIconId = R.drawable.ic_gradient_user,
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.OnNameChange(it))
                },
                hint = stringResource(id = R.string.name)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = state.surname,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.OnSurnameChange(it))
                },
                hint = stringResource(id = R.string.surname)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.OnPasswordChange(it))
                },
                hint = stringResource(id = R.string.password),
                trailingIconId = state.passwordTextFieldIconId,
                onTrailingIconClick = {
                    viewModel.onEvent(RegisterEvent.OnTogglePasswordVisibility)
                },
                isTrailingIconEnabled = !state.isTryingToRegister,
                visualTransformation = if (state.shouldHidePassword) {
                    VisualTransformation {
                        TransformedText(
                            AnnotatedString("*".repeat(it.length)),
                            OffsetMapping.Identity
                        )
                    }
                } else VisualTransformation.None
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = state.repeatPassword,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.OnRepeatPasswordChange(it))
                },
                hint = stringResource(id = R.string.repeat_password)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.isStudent,
                    onCheckedChange = {
                        viewModel.onEvent(RegisterEvent.OnToggleIsStudent(it))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = BrightOrange,
                        uncheckedColor = Color.Gray,
                        checkmarkColor = TextWhite
                    )
                )
                Text(
                    text = stringResource(id = R.string.are_you_tul_student),
                    color = TextWhite,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable(
                            enabled = !state.isTryingToRegister,
                            onClick = {
                                viewModel.onEvent(
                                    RegisterEvent.OnToggleIsStudent(
                                        !state.isStudent
                                    )
                                )
                            }
                        )
                )
            }
            if (state.isStudent) {
                CustomTextField(
                    value = state.studentId,
                    onValueChange = {
                        viewModel.onEvent(RegisterEvent.OnStudentIdChange(it))
                    },
                    hint = stringResource(id = R.string.student_id),
                    keyboardType = KeyboardType.Number
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradientButton(
                text = stringResource(id = R.string.sign_up),
                isEnabled = !state.isTryingToRegister,
                onClick = {
                    viewModel.onEvent(
                        RegisterEvent.OnRegisterClick(
                            RegisterUserInfo(
                                name = state.name,
                                surname = state.surname,
                                email = state.email,
                                password = state.password,
                                studentId = if (state.isStudent && state.studentId.isNotBlank()) {
                                    state.studentId
                                } else null
                            )
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.have_account_already) + " ",
                    color = TextWhite,
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp
                )
                Text(
                    text = stringResource(id = R.string.sign_in),
                    color = BrightOrange,
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable(
                        enabled = !state.isTryingToRegister,
                        onClick = {
                            onNavigation()
                        }
                    )
                )
            }
        }
    }

    if (state.isTryingToRegister) {
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