package com.example.c17_129_kotlin.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.c17_129_kotlin.fireman.ImageDisplayMain
import com.example.c17_129_kotlin.home.ui.ScreenMainMenu
import com.example.c17_129_kotlin.login.LogScreen
import com.example.c17_129_kotlin.necessaryWorks.NecesssaryWorksScreen
import com.example.c17_129_kotlin.pharmacy.PharmacyScreen
import com.example.c17_129_kotlin.police.PoliceScreen
import com.example.c17_129_kotlin.reportScreen.ReporteScreen

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
            ReporteScreen()
        }
    }
}