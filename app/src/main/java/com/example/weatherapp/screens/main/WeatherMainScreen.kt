package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.screens.settings.SettingsViewModel
import com.example.weatherapp.utils.getDateTime
import com.example.weatherapp.widgets.CustomDivider
import com.example.weatherapp.widgets.CustomWeatherImgDescriber
import com.example.weatherapp.widgets.WeatherAppBar
import com.example.weatherapp.widgets.WeatherDetails

@Composable
fun WeatherMainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
){

    val curCity: String = if(city.isNullOrBlank()) "Cluj" else city
    val unitFromDb = settingsViewModel.unitsList.collectAsState().value
    var unit = remember{ mutableStateOf("metric") }
    var isMetric = remember{ mutableStateOf(false) }


    if(!unitFromDb.isNullOrEmpty()){
        unit.value = unitFromDb[0].unit.split(" ")[0].lowercase()
        isMetric.value = unit.value == "metric"
        val weatherData = produceState<DataOrException<CityWeather,Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ){
            value = city?.let { mainViewModel.getWeatherData(city = curCity , unit = unit.value) }!!
        }.value

        if(weatherData.loading == true){
            CircularProgressIndicator()
        }else if(weatherData.data != null){
            MainScaffold(weatherData.data!!, navController, isMetric = isMetric)
        }
    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    weather: CityWeather,
    navController: NavController,
    isMetric: MutableState<Boolean>
){

    Scaffold(topBar = {
        WeatherAppBar(
            navController = navController,
            title = weather.name + ", " + weather.sys.country,
//            icon = Icons.Default.ArrowBack,
        onAddActionClicked = {
            navController.navigate(WeatherScreens.SearchScreen.name)
        }
        ){
            Log.d("TAG", "MainScaffold: Button clicked")
        }
    }) {
         MainContent(data = weather, isMetric = isMetric)
    }
}


@Composable
fun MainContent(
    data: CityWeather,
    isMetric: MutableState<Boolean>
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

            Text(
                text = getDateTime(data.dt, "EEE, MMM d"),
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
                border = BorderStroke(width = 3.dp, color = colorResource(id = R.color.dark_purple))

            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    when (data.weather[0].main) {
                        "Clouds" -> if (data.weather[0].description == "broken clouds") {
                            CustomWeatherImgDescriber(img = painterResource(id = R.drawable.clear_sky))
                        } else {
                            CustomWeatherImgDescriber(img = painterResource(id = R.drawable.cloudy))
                        }
                        "Rain" -> CustomWeatherImgDescriber(img = painterResource(id = R.drawable.light_rain))
                        "Thunderstorm" -> CustomWeatherImgDescriber(img = painterResource(id = R.drawable.storm))
                        "Snow" -> CustomWeatherImgDescriber(img = painterResource(id = R.drawable.snow))
                        "Clear" -> CustomWeatherImgDescriber(img = painterResource(id = R.drawable.sunny))

                        else -> {
                            CustomWeatherImgDescriber(img = painterResource(id = R.drawable.cloudy))
                        }
                    }




                    Text(
                        text = data.main.temp.toInt().toString() + "°",
                        color = colorResource(id = R.color.egg),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    Text(
                        text = data.weather[0].main,
                        color = colorResource(id = R.color.egg),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


            }

            // pressure, humidity, wind
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp)
            ) {
                WeatherDetails(
                    img = painterResource(id = R.drawable.pressure),
                    description = data.main.pressure.toString() + " psi"
                )
                WeatherDetails(
                    img = painterResource(id = R.drawable.humidity),
                    description = data.main.humidity.toString() + " %"
                )
                if (isMetric.value){
                    WeatherDetails(
                        img = painterResource(id = R.drawable.wind),
                        description = data.wind.speed.toInt().toString() + " km/h"
                    )

                }else{
                    WeatherDetails(
                        img = painterResource(id = R.drawable.wind),
                        description = data.wind.speed.toInt().toString() + " mph"
                    )

                }

            }
            CustomDivider()
            // sunset and sunrise
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp)
            ) {
                WeatherDetails(
                    img = painterResource(id = R.drawable.sunrise),
                    description = getDateTime(data.sys.sunrise, "hh:mm aa")
                )

                WeatherDetails(
                    img = painterResource(id = R.drawable.sunset),
                    description = getDateTime(data.sys.sunset, "hh:mm aa")
                )

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                colorResource(id = R.color.light_purple),
                                colorResource(id = R.color.dark_purple),

                                )
                        )
                    ),
                contentAlignment = Alignment.TopCenter

            ) {
                Column (modifier = Modifier.padding(top = 20.dp)){
                    Text(
                        text = "More info:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(top = 10.dp),
                    )
                    Text(
                        text = "Description: "  + data.weather[0].description,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )

                    Text(
                        text = "Feels like: "  + data.main.feels_like.toInt(),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                    Text(
                        text = "Max temp: "  + data.main.temp_max.toInt(),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                    Text(
                        text = "Min temp: " + data.main.temp_min.toInt(),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }



            }


        }


    }


}


