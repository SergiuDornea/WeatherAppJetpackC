package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.splash.WeatherSplashScreen
import com.example.weatherapp.screens.main.WeatherMainScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            // create the actual screen
            WeatherSplashScreen(navController = navController)
        }
        composable(WeatherScreens.MainScreen.name){
            // create the actual screen
            WeatherMainScreen(navController = navController)
        }
    }
}