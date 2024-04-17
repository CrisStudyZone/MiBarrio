package com.example.c17_129_kotlin.home.navigation

sealed class HomeScreens(
    val route : String
){

    object HomeScreen : HomeScreens(
        route = "home_screen"
    )
    object LoginScreen : HomeScreens(
        route = "login_screen"
    )
}