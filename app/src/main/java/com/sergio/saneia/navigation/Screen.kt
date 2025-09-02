package com.sergio.saneia.navigation

sealed class Screen(val route:String) {
    object HomeScreen: Screen("home")
    object SecondScreen: Screen("second")
    object ThirdScreen: Screen("third")
    object FourScreen: Screen("four")

}