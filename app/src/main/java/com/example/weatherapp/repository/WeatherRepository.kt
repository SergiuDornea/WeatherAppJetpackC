package com.example.weatherapp.repository


import android.util.Log
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

// get the data from the WeatherApi and check
class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String, units: String ) : DataOrException<CityWeather, Boolean , Exception>{
        val response = try{
            api.getWeather(query = cityQuery)
        }catch (e: Exception){
            Log.d("REX", "getWeather: big  problem :)) ")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)

    }

}