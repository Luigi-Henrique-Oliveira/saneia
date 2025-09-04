package com.sergio.saneia

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sergio.saneia.navigation.ButtonNavItem
import com.sergio.saneia.navigation.NavGraph
import com.sergio.saneia.navigation.Screen

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val items = listOf(
        ButtonNavItem(Screen.HomeScreen.route, Icons.Default.Home, "Inicio"),
        ButtonNavItem(Screen.SecondScreen.route, painter = painterResource(id = R.drawable.baseline_map_24), label = "Local"),
        ButtonNavItem(Screen.ThirdScreen.route, painter = painterResource(id = R.drawable.baseline_videogame_asset_24), label = "Quiz"),
        ButtonNavItem(Screen.FourScreen.route,Icons.Default.Settings,"NoN")

    )
    Scaffold(
        bottomBar = {
            NavigationBar(
            ) {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentRoute  = navBackStackEntry.value?.destination?.route

                items.forEach{ item:ButtonNavItem->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                                  if(currentRoute!=item.route){
                                      navController.navigate(item.route){
                                          popUpTo(navController.graph.startDestinationId){ saveState = true }
                                          launchSingleTop = true
                                          restoreState = true
                                      }
                                  }
                        },
                        icon = {
                            when{
                                item.icon != null ->Icon(imageVector = item.icon, contentDescription = item.label)
                                item.painter!= null-> Icon(painter = item.painter , contentDescription = item.label)
                        }},
                        label = { Text(text = item.label)}
                    )


                }
            }
        }
    ){
        NavGraph(modifier = Modifier.padding(it), navHostController = navController)
    }
}