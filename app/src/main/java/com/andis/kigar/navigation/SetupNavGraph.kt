package com.andis.kigar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andis.kigar.view.AddDataScreen
import com.andis.kigar.view.AnimatedSplashScreen
import com.andis.kigar.view.ListDataScreen
import com.andis.kigar.view.LoginScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            AnimatedSplashScreen(navController = navController)
        }

        composable(
            route = Screen.AddData.route
        ) {
            AddDataScreen(navController = navController)
        }

        composable(
            route = Screen.ListData.route
        ) {
            ListDataScreen(navController = navController)
        }

        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController = navController)
        }
    }
}