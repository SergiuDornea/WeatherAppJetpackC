package com.example.weatherapp.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.navigation.ShowData

@Composable
fun WeatherMainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()){
    ShowData(mainViewModel = mainViewModel)
}