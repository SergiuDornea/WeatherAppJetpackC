package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAboutScreen(navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            navController = navController,
            isOnMainScreen = false,
            icon = Icons.Default.ArrowBack
        ){
            // return when arrow clicked
            navController.popBackStack()
        }
    }){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.light_purple)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(text = "Weather App v.0.1", fontWeight = FontWeight.SemiBold)
            Text(text = "Weather API by:", fontWeight = FontWeight.SemiBold)
            Text(text = "https://openweathermap.org")

        }
    }
}