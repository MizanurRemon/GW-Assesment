package com.example.gw_assesment.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gw_assesment.UpdateAccountScreen
import com.example.gw_assesment.create_task.CreateTaskScreen
import com.example.gw_assesment.create_task.CreateTaskViewModel
import com.example.gw_assesment.details.DetailsScreen
import com.example.gw_assesment.details.DetailsViewModel
import com.example.gw_assesment.home.HomeScreen
import com.example.gw_assesment.home.HomeViewModel
import com.example.gw_assesment.login.LoginScreen
import com.example.gw_assesment.login.LoginViewModel
import com.example.gw_assesment.splash.SplashScreen
import com.example.gw_assesment.splash.SplashViewModel
import com.example.gw_assesment.utils.Route

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.SPLASH,
            modifier = Modifier
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            composable(Route.SPLASH) {
                val viewModel = hiltViewModel<SplashViewModel>()
                SplashScreen(
                    uiEvent = viewModel.uiEvent,
                    onNavigation = { route ->
                        navController.navigate(route)
                    }
                )
            }

            composable(route = Route.LOGIN) {
                val viewModel = hiltViewModel<LoginViewModel>()
                LoginScreen(
                    snackBarHostState = snackBarHostState,
                    onEvent = viewModel::onEvent,
                    state = viewModel.state,
                    uiEvent = viewModel.uiEvent,
                    onHome = {
                        navController.navigate(Route.HOME) {
                            //popUpTo(navController.graph.id) {}
                        }
                    }
                )
            }

            composable(route = Route.HOME) {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(
                    state = viewModel.state,
                    onNavigate = { route ->
                        navController.navigate(route)
                    },
                    onItemClick = {
                        navController.navigate(Route.DETAILS)
                    }
                )
            }

            composable(Route.CREATE) {
                val viewModel = hiltViewModel<CreateTaskViewModel>()
                CreateTaskScreen(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent,
                    uiEvent = viewModel.uiEvent,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.DETAILS) {
                val viewModel = hiltViewModel<DetailsViewModel>()
                DetailsScreen(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent,
                    uiEvent = viewModel.uiEvent,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Route.UPDATE_ACCOUNT) {

                UpdateAccountScreen(
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }

}