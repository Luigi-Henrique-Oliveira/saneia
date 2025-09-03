package com.sergio.saneia.navigation

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sergio.saneia.screens.FourScreen
import com.sergio.saneia.screens.HomeScreen
import com.sergio.saneia.screens.SecondScreen
import com.sergio.saneia.screens.ThirdScreen


@Composable
fun NavGraph(
    modifier: Modifier,
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
        ){
        composable(Screen.HomeScreen.route){
            HomeScreen()
        }
        composable(Screen.SecondScreen.route){
            SecondScreen()
        }
        composable(Screen.ThirdScreen.route){
            ThirdScreen()
        }
        composable(Screen.FourScreen.route){
            FourScreen()
        }
    }
}
