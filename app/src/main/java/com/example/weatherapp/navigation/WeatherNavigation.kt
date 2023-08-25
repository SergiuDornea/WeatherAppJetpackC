package com.example.weatherapp.navigation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.screens.main.MainViewModel
import com.example.weatherapp.screens.main.WeatherMainScreen
import com.example.weatherapp.screens.splash.WeatherSplashScreen

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
            val mainViewModel = hiltViewModel<MainViewModel>()
            WeatherMainScreen(navController = navController, mainViewModel )

        }
    }
}

@Composable
fun ShowData(mainViewModel: MainViewModel){
    val weatherData = produceState<DataOrException<CityWeather,Boolean, Exception>>(
        initialValue = DataOrException(loading = true)){
        value = mainViewModel.getWeatherData(city = "Bucharest")
    }.value

    if(weatherData.loading == true){
        CircularProgressIndicator()
    }else if(weatherData.data != null){
        Text(" ${weatherData.data.toString()}")
    }
}