package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screens.WeatherAboutScreen
import com.example.weatherapp.screens.favorite.WeatherFavoriteScreen
import com.example.weatherapp.screens.main.MainViewModel
import com.example.weatherapp.screens.main.WeatherMainScreen
import com.example.weatherapp.screens.search.WeatherSearchScreen
import com.example.weatherapp.screens.settings.WeatherSettingsScreen
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
            route = "${WeatherScreens.MainScreen.name}/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType }
        )){backStackEntry ->
            // create the actual screen
            val city = backStackEntry.arguments?.getString("city") ?: ""
            val mainViewModel = hiltViewModel<MainViewModel>()
            WeatherMainScreen(navController = navController, mainViewModel = mainViewModel, city = city)

        }
        composable(WeatherScreens.SearchScreen.name){
            // create the actual screen
            WeatherSearchScreen(navController = navController)

        }
        composable(WeatherScreens.AboutScreen.name){
            // create the actual screen
            WeatherAboutScreen(navController = navController)

        }
        composable(WeatherScreens.SettingsScreen.name){
            // create the actual screen
            WeatherSettingsScreen(navController = navController)

        }
        composable(WeatherScreens.FavoriteScreen.name){
            // create the actual screen
            WeatherFavoriteScreen(navController = navController)

        }
    }
}

