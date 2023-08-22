package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R

@Preview(showBackground = true)
@Composable
fun WeatherSplashScreen(navController: NavController = rememberNavController()){
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.border(
                5.dp,
                brush = Brush.linearGradient(listOf( Color.Blue, Color.Cyan)),
                shape = CircleShape
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(painter = painterResource(
                id =  R.drawable.splash_weather),
                contentDescription = "Sun icon",

                )
            Text(
                text = "How's the weather?",
                color = Color.Cyan,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,

                )

        }


    }
}