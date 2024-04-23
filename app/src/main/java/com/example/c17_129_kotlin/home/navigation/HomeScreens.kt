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
    object FiremanScreens : HomeScreens(
        route = "fireman_screen"
    )
    object PharmacyScreens : HomeScreens(
        route = "pharmacy_screen"
    )
    object PoliceScreens : HomeScreens(
        route = "police_screen"
    )
    object NecessaryWorksScreen : HomeScreens(
        route = "NecessaryWorks_screen"
    )
}