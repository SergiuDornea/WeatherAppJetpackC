package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.widgets.CustomDivider
import com.example.weatherapp.widgets.WeatherAppBar
import com.example.weatherapp.widgets.WeatherDetails

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
fun MainContent(
    data: CityWeather
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.light_purple)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO get the date and format it
            Text(
                text = "Mon, Nov 28",
                modifier = Modifier
                    .padding(top = 70.dp),
                color = colorResource(id = R.color.egg),
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic
                
            )

            Surface(
                shape = CircleShape,
                color = colorResource(id = R.color.baby_blue),
                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp),
                border = BorderStroke(width = 3.dp, color = colorResource(id = R.color.dark_purple) )

            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //TODO make photo change according to the data
                    Image(
                        painter = painterResource(id = R.drawable.sunny),
                        contentDescription = "weather img",
                        modifier = Modifier
                            .size(50.dp)
                        )
                    //TODO get the temp
                    Text(
                        text = "24°",
                        color = colorResource(id = R.color.egg),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    // TODO get the weather status
                    Text(
                        text = "Sunny",
                        color = colorResource(id = R.color.egg),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                ) {
                WeatherDetails(
                    img =painterResource(id = R.drawable.pressure) ,
                    description = "124 Psi"
                )
                WeatherDetails(
                    img =painterResource(id = R.drawable.humidity) ,
                    description = "17%"
                     )
                WeatherDetails(img =painterResource(id = R.drawable.wind) ,
                    description = "10 km/h"
                    )
            }
            CustomDivider()

        }


    }




}
