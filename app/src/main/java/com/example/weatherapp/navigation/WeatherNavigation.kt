package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screens.main.MainViewModel
import com.example.weatherapp.screens.main.WeatherMainScreen
import com.example.weatherapp.screens.search.WeatherSearchScreen
import com.example.weatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            // create the actual screen
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable(
            "$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                }
        )){
            // create the actual screen
            it.arguments?.getString("city").let {
                val mainViewModel = hiltViewModel<MainViewModel>()
                if (it != null) {
                    WeatherMainScreen(navController = navController, mainViewModel , city = it)
                }
            }
        }

        composable(WeatherScreens.SearchScreen.name){
            // create the actual screen
            WeatherSearchScreen(navController = navController)

        }
    }
}

