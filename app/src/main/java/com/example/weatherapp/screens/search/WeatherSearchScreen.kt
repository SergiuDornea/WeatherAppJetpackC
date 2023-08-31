package com.example.weatherapp.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.widgets.CustomSearchBar
import com.example.weatherapp.widgets.WeatherAppBar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherSearchScreen(navController: NavController){
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            navController = navController,
            isOnMainScreen = false,
            icon = Icons.Default.ArrowBack
            ){
            // return when arrow clicked
            navController.popBackStack()
        }
    }) {
        Surface() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                CustomSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                ){
                    navController.navigate(WeatherScreens.MainScreen.name + "/$it")
                }


            }
        }
    }
}

