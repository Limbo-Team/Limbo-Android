package com.igorj.limboapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.igorj.limboapp.components.CircleImage
import com.igorj.limboapp.components.Flickers
import com.igorj.limboapp.components.LimboLogo
import com.igorj.limboapp.ui.theme.DarkBackground
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.screen.chapters.ChaptersScreen
import com.igorj.limboapp.screen.home.HomeScreen
import com.igorj.limboapp.screen.profile.ProfileScreen
import com.igorj.limboapp.screen.stats.StatsScreen
import com.igorj.limboapp.navigation.Route
import com.igorj.limboapp.ui.theme.LimboAppTheme
import com.igorj.limboapp.screen.finish_quiz.FinishQuizScreen
import com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordScreen
import com.igorj.limboapp.screen.forgot_password.enter_email.ForgotPasswordScreen
import com.igorj.limboapp.screen.forgot_password.enter_verification_code.VerificationCodeScreen
import com.igorj.limboapp.screen.login.LoginScreen
import com.igorj.limboapp.screen.playing_quiz.PlayingQuizScreen
import com.igorj.limboapp.screen.quizzes.QuizzesScreen
import com.igorj.limboapp.screen.register.RegisterScreen
import com.igorj.limboapp.screen.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authApi: AuthAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.apply {
            statusBarColor = DarkBackground.toArgb()
            navigationBarColor = DarkBackground.toArgb()
        }
        val startDestination = if (authApi.getToken().isBlank()) {
            Route.WELCOME
        } else {
            Route.HOME
        }
        setContent {
            LimboAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                MainScreen(
                    navController = navController,
                    scaffoldState = scaffoldState,
                    startDestination = startDestination
                )
            }
        }
    }
}