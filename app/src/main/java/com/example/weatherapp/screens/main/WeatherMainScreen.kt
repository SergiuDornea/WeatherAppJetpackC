package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.widgets.WeatherAppBar

@Composable
fun WeatherMainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
    val weatherData = produceState<DataOrException<CityWeather,Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ){
        value = mainViewModel.getWeatherData(city = "Bucharest")
    }.value

    if(weatherData.loading == true){
        CircularProgressIndicator()
    }else if(weatherData.data != null){
        MainScaffold(weatherData.data!!, navController)
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: CityWeather , navController: NavController){

    Scaffold(topBar = {
        WeatherAppBar(navController = navController,
            title = weather.name + ", " + weather.sys.country,
//            icon = Icons.Default.ArrowBack,
        ){
            Log.d("TAG", "MainScaffold: Button clicked")
        }
    }) {
         MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: CityWeather) {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.light_purple)
    ) {
        Text(text = data.main.feels_like.toString())
    }




}
