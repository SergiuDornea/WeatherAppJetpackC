package com.example.weatherapp.screens.main


import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city : String) : DataOrException<CityWeather, Boolean, Exception>{
        return repository.getWeather(cityQuery = city)
    }

//    private fun getWeather(city: String) {
//        viewModelScope.launch {
//            if(city.isEmpty()) return@launch
//            data.value.loading = true
//            data.value = repository.getWeather(cityQuery = city, units = "metric")
//            if(data.value.data.toString().isNotEmpty()) data.value.loading = false
//        }
//        Log.d("Get", "getWeather: ${data.value.data.toString()}")
//    }

}