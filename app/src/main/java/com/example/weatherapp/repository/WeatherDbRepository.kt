package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavorites():Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(fav: Favorite) = weatherDao.insertFavourite(favorite = fav)
    suspend fun updateFavorite(fav: Favorite) = weatherDao.updateFavourite(favorite = fav)
    suspend fun deleteFavorite(fav: Favorite) = weatherDao.deleteFavourite(favorite = fav)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavourites()
    suspend fun getFavById(city: String) = weatherDao.getFavById(city)

    fun getUnits():Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)

}