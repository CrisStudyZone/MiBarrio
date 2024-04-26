package com.example.c17_129_kotlin.feature_home.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.c17_129_kotlin.camera.CameraScreen
import com.example.c17_129_kotlin.fireman.ImageDisplayMain
import com.example.c17_129_kotlin.feature_home.ui.screen.ScreenMainMenu
import com.example.c17_129_kotlin.feature_authentication.login.LogScreen
import com.example.c17_129_kotlin.feature_home.ui.screen.NecesssaryWorksScreen
import com.example.c17_129_kotlin.feature_home.ui.screen.PharmacyScreen
import com.example.c17_129_kotlin.feature_home.ui.screen.PoliceScreen
import com.example.c17_129_kotlin.feature_home.ui.screen.ReporteScreen

@Composable
fun HomeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreens.LoginScreen.route
    ) {
        composable(route = HomeScreens.HomeScreen.route) {
            ScreenMainMenu(navController = navController)
        }
        composable(route = HomeScreens.LoginScreen.route) {
            LogScreen(navController = navController)
        }
        composable(route = HomeScreens.FiremanScreens.route){
            ImageDisplayMain()
        }
        composable(route = HomeScreens.PharmacyScreens.route){
            PharmacyScreen()
        }
        composable(route = HomeScreens.PoliceScreens.route){
            PoliceScreen()
        }
        composable(route = HomeScreens.NecessaryWorksScreen.route){
            NecesssaryWorksScreen()
        }
        composable(route = HomeScreens.ReportScreens.route){
            ReporteScreen(navController = navController)
        }
        composable(route = HomeScreens.CameraScreen.route){
            CameraScreen(navController = navController)
        }
    }
}