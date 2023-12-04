package com.igorj.limboapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igorj.auth_presentation.login.LoginScreen
import com.igorj.auth_presentation.register.RegisterScreen
import com.igorj.auth_presentation.welcome.WelcomeScreen
import com.igorj.dashboard_presentation.chapters.ChaptersScreen
import com.igorj.dashboard_presentation.home.HomeScreen
import com.igorj.dashboard_presentation.profile.ProfileScreen
import com.igorj.dashboard_presentation.stats.StatsScreen
import com.igorj.limboapp.navigation.Route
import com.igorj.limboapp.ui.theme.LimboAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        startDestination = Route.WELCOME,
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
                        composable(Route.QUIZ_PLAY) {

                        }
                        composable(Route.QUIZ_FINISH) {

                        }
                    }
                }
            }
        }
    }
}