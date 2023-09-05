package com.example.weatherapp.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherSettingsScreen(navController: NavController){
   var unitToggleState = remember { mutableStateOf(false) }
   val measurementUnits = listOf("Imperial (F)", "Metric (C)")
   var choiceState = remember { mutableStateOf("") }
   Scaffold(topBar = { WeatherAppBar(
      title = "Settings",
      navController = navController,
      icon = Icons.Default.ArrowBack,
      isOnMainScreen = false
      )}) {
         Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.light_purple)
            ) {
            Column(
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
               ) {
               Text(
                  text = "Change units of measurement",
                  modifier = Modifier.padding(bottom = 20.dp),
                  color = colorResource(id = R.color.egg)
                  )
               IconToggleButton(
                  checked = !unitToggleState.value,
                  onCheckedChange ={
                  unitToggleState.value = !it
                  if(unitToggleState.value) {
                     choiceState.value = "Metric (C)"
                  }else{
                     choiceState.value = "Imperial (F)"
                  } },
                  modifier = Modifier
                     .fillMaxWidth(0.5f)
                     .clip(RectangleShape)
                     .padding(5.dp)
                     .background(colorResource(id = R.color.dark_purple))

                  ){
                  Text(text = if(unitToggleState.value) "Fahrenheit °F" else "Celsius °C", color= colorResource(id = R.color.egg))


               }
            }

         }
   }
}