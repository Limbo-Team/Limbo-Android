package com.igorj.limboapp

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.igorj.limboapp.components.BottomNavBar
import com.igorj.limboapp.components.CircleImage
import com.igorj.limboapp.components.Flickers
import com.igorj.limboapp.components.LimboLogo
import com.igorj.limboapp.components.LogoutButton
import com.igorj.limboapp.components.bottomNavBarItems
import com.igorj.limboapp.navigation.Route
import com.igorj.limboapp.screen.chapters.ChaptersScreen
import com.igorj.limboapp.screen.finish_quiz.FinishQuizScreen
import com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordScreen
import com.igorj.limboapp.screen.forgot_password.enter_email.ForgotPasswordScreen
import com.igorj.limboapp.screen.forgot_password.enter_verification_code.VerificationCodeScreen
import com.igorj.limboapp.screen.home.HomeScreen
import com.igorj.limboapp.screen.login.LoginScreen
import com.igorj.limboapp.screen.playing_quiz.PlayingQuizScreen
import com.igorj.limboapp.screen.profile.ProfileScreen
import com.igorj.limboapp.screen.quizzes.QuizzesScreen
import com.igorj.limboapp.screen.register.RegisterScreen
import com.igorj.limboapp.screen.stats.StatsScreen
import com.igorj.limboapp.screen.welcome.WelcomeScreen
import com.igorj.limboapp.util.UiEvent

@Composable
fun MainScreen(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    startDestination: String,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val screensWithTopLogo = listOf(
        Route.WELCOME,
        Route.LOGIN,
        Route.REGISTER,
        Route.FORGOT_PASSWORD,
        Route.VERIFICATION_CODE,
        Route.CHANGE_PASSWORD
    )
    val screensWithTopAndBottomBar = listOf(
        Route.HOME,
        Route.CHAPTERS,
        Route.STATS,
        Route.PROFILE,
        Route.REDEEM_FLICKERS,
        Route.QUIZ_START,
        "${Route.QUIZZES}/{chapterId}"
    )
    val gson = Gson()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnNavigate -> {
                    navController.navigate(Route.LOGIN)
                }
                else -> {

                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            if (screensWithTopLogo.contains(currentRoute)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp)
                ) {
                    LimboLogo(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else if (screensWithTopAndBottomBar.contains(currentRoute)) {
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
                            viewModel.onEvent(MainEvent.OnFlickersClick)
                        },
                        flickers = state.flickers
                    )
                    LimboLogo(
                        modifier = Modifier.align(Alignment.Center)
                    )
                    if (currentRoute == Route.PROFILE) {
                        LogoutButton(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 26.dp),
                            onClick = {
                                viewModel.onEvent(MainEvent.OnLogoutClick)
                            }
                        )
                    } else {
                        CircleImage(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 30.dp),
                            imageUrl = state.userInfo.image,
                            contentDescription = stringResource(id = R.string.profile),
                            size = 40.dp,
                            onClick = {
                                Log.d("LOGCAT profile", "${state.userInfo}")
                            }
                        )
                    }
                }
            }
        },
        content = {
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
                            viewModel.updateTopBarInfo()
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
                        mainViewModel = viewModel,
                        onNavigation = { route ->
                            Log.d("LOGCAT", "MainScreen: ${navController.currentDestination?.route}")
                            navController.navigate(
                                route = route,
                                navOptions = NavOptions.Builder()
                                    .setPopUpTo(
                                        route = Route.HOME,
                                        inclusive = true
                                    )
                                    .build()
                            )
                        },
                        onMiniChapterNavigation = { chapterId ->
                            navController.navigate(
                                route = "${Route.QUIZZES}/$chapterId",
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
                        mainViewModel = viewModel,
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
                                route = "${Route.QUIZZES}/$chapterId",
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
                composable(
                    route = "${Route.QUIZZES}/{chapterId}",
                    arguments = listOf(navArgument("chapterId") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) { entry ->
                    QuizzesScreen(
                        chapterId = entry.arguments?.getString("chapterId").toString(),
                        onNavigation = { route ->
                            navController.navigate(
                                route = route,
                                navOptions = NavOptions.Builder()
                                    .setPopUpTo(
                                        route = Route.QUIZZES,
                                        inclusive = true
                                    )
                                    .build()
                            )
                        },
                        onQuizClick = { quizId ->
                            navController.navigate(
                                route = "${Route.QUIZ_PLAY}/$quizId",
                                navOptions = NavOptions.Builder()
                                    .setPopUpTo(
                                        route = Route.QUIZZES,
                                        inclusive = true
                                    )
                                    .build()
                            )
                        }
                    )
                }
                composable(Route.STATS) {
                    StatsScreen(
                        mainViewModel = viewModel,
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
                    ProfileScreen(
                        mainViewModel = viewModel,
                        onNavigation = { route ->
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
                    route = "${Route.QUIZ_PLAY}/{quizId}",
                    arguments = listOf(navArgument("quizId") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) { entry ->
                    PlayingQuizScreen(
                        mainViewModel = viewModel,
                        quizId = entry.arguments?.getString("quizId").toString(),
                        onNavigation = { finishedQuizResponse ->
                            val finishedQuizResponseAsJson = gson.toJson(finishedQuizResponse)
                            navController.navigate(
                                route = "${Route.QUIZ_FINISH}/${finishedQuizResponseAsJson}",
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
                composable(
                    route = "${Route.QUIZ_FINISH}/{finishedQuizResponseAsJson}",
                    arguments = listOf(navArgument("finishedQuizResponseAsJson") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) { entry ->
                    FinishQuizScreen(
                        finishedQuizResponseAsJson = entry.arguments?.getString("finishedQuizResponseAsJson"),
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
        },
        bottomBar = {
            if (screensWithTopAndBottomBar.contains(currentRoute)) {
                BottomNavBar(
                    items = bottomNavBarItems,
                    selectedItemRoute = currentRoute ?: "",
                    onItemClick = {
                        navController.navigate(it.route) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    )
}