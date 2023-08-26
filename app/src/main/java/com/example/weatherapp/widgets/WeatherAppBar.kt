package com.example.weatherapp.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WeatherAppBar(
    title : String = "Bucharest RO",
    icon : ImageVector? = null,
    isOnMainScreen : Boolean = true,
    elevation: Dp = 0.dp,
//    navController: NavController ,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
){
    TopAppBar(
        title = { Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        ) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Blue
        )

    )


}