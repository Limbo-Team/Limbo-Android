package com.igorj.limboapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNavigateToLogin = {
                                    navController.navigate(Route.LOGIN)
                                },
                                onNavigateToRegister = {
                                    navController.navigate(Route.REGISTER)
                                }
                            )
                        }
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                onLoginClick = {
                                    navController.navigate(Route.HOME)
                                },
                                onRegisterClick = {
                                    navController.navigate(Route.REGISTER)
                                },
                                onForgotPasswordClick = {
                                    navController.navigate(Route.FORGOT_PASSWORD)
                                }
                            )
                        }
                        composable(Route.REGISTER) {
                            RegisterScreen(
                                scaffoldState = scaffoldState,
                                onNavigation = {
                                    navController.navigate(Route.LOGIN)
                                }
                            )
                        }
                        composable(Route.FORGOT_PASSWORD) {
                            ForgotPasswordScreen(
                                scaffoldState = scaffoldState,
                                onNavigation = {
                                    navController.navigate(Route.VERIFICATION_CODE)
                                }
                            )
                        }
                        composable(Route.VERIFICATION_CODE) {
                            VerificationCodeScreen(
                                scaffoldState = scaffoldState,
                                onNavigation = {
                                    navController.navigate(Route.CHANGE_PASSWORD)
                                }
                            )
                        }
                        composable(Route.CHANGE_PASSWORD) {
                            ChangePasswordScreen(
                                scaffoldState = scaffoldState,
                                onNavigation = {

                                }
                            )
                        }
                        composable(Route.HOME) {
                            HomeScreen(
                                onNavigation = { route ->
                                    navController.navigate(
                                        route = route,
                                        navOptions = NavOptions.Builder()
                                            .setPopUpTo(
                                                route = Route.HOME,
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                }
                            )
                        }
                        composable(Route.CHAPTERS) {
                            ChaptersScreen(
                                onNavigation = { route ->
                                    navController.navigate(
                                        route = route,
                                        navOptions = NavOptions.Builder()
                                            .setPopUpTo(
                                                route = Route.CHAPTERS,
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                },
                                onChapterNavigation = { chapterId ->
                                    navController.navigate(
                                        route = "${Route.QUIZ_PLAY}/$chapterId",
                                        navOptions = NavOptions.Builder()
                                            .setPopUpTo(
                                                route = Route.CHAPTERS,
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                }
                            )
                        }
                        composable(Route.STATS) {
                            StatsScreen(
                                scaffoldState = scaffoldState,
                                onNavigation = { route ->
                                    navController.navigate(
                                        route = route,
                                        navOptions = NavOptions.Builder()
                                            .setPopUpTo(
                                                route = Route.STATS,
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                }
                            )
                        }
                        composable(Route.PROFILE) {
                            ProfileScreen(onNavigation = { route ->
                                navController.navigate(
                                    route = route,
                                    navOptions = NavOptions.Builder()
                                        .setPopUpTo(
                                            route = Route.PROFILE,
                                            inclusive = true
                                        )
                                        .build()
                                    )
                                }
                            )
                        }
                        composable(Route.REDEEM_FLICKERS) {

                        }
                        composable(Route.QUIZ_START) {

                        }
                        composable(
                            route = "${Route.QUIZ_PLAY}/{chapterId}",
                            arguments = listOf(navArgument("chapterId") {
                                type = NavType.IntType
                                defaultValue = 0
                            })
                        ) {
                            PlayingQuizScreen(
                                onNavigation = {
                                    navController.navigate(
                                        route = Route.QUIZ_FINISH,
                                        navOptions = NavOptions.Builder()
                                            .setPopUpTo(
                                                route = Route.QUIZ_PLAY,
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                }
                            )
                        }
                        composable(Route.QUIZ_FINISH) {
                            FinishQuizScreen(
                                onNavigation = {
                                    navController.navigate(
                                        route = Route.CHAPTERS,
                                        navOptions = NavOptions.Builder()
                                            .setPopUpTo(
                                                route = Route.QUIZ_FINISH,
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}