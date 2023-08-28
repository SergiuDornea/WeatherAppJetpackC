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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.widgets.CustomDivider
import com.example.weatherapp.widgets.CustomListITem
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
    val dataList = listOf(
        data.coord.lat.toString(),
        data.coord.lon.toString(),
        data.base,
        data.main.feels_like.toString(),
        data.main.temp_min.toString(),
        data.main.temp_max.toString(),
        data.visibility.toString(),
        data.clouds.toString(),
        data.timezone.toString()
        )
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

            // TODO get the actual data
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp)
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
            // TODO get the actual data
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp)
            ) {
                WeatherDetails(
                    img =painterResource(id = R.drawable.sunrise) ,
                    description = "06:12 AM"
                )
                WeatherDetails(
                    img =painterResource(id = R.drawable.sunset) ,
                    description = "09:00 PM"
                )

            }
            // TODO add a  lazy list , and replace dummy data
            Text(text = "More info")
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 15.dp)
            ){
                items(items = dataList){
                    CustomListITem(data = it)
                }
            }
        }


        }





}

