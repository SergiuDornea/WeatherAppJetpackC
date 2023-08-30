package com.example.weatherapp.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
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
                CustomSearchBar()


            }
        }
    }
}

