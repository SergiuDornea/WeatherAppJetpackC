package com.example.weatherapp.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    val data: MutableState<DataOrException<CityWeather, Boolean , Exception>> = mutableStateOf(
        DataOrException(null, null, Exception("")))

    init{
        loadWrather()

    }

    private fun loadWrather() {
        getWeather("Bucharest")
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            if(city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city, units = "metric")
            if(data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
        Log.d("Get", "getWeather: ${data.value.data.toString()}")
    }

}