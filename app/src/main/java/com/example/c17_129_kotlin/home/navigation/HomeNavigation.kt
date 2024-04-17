package com.example.c17_129_kotlin.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.c17_129_kotlin.MyApp
import com.example.c17_129_kotlin.home.ScreenMainMenu
import com.example.c17_129_kotlin.login.Log

@Composable
fun HomeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreens.LoginScreen.route
    ) {
        composable(route = HomeScreens.HomeScreen.route) {
            ScreenMainMenu()
        }
        composable(route = HomeScreens.LoginScreen.route) {
            Log(navController = navController)
        }
    }
}