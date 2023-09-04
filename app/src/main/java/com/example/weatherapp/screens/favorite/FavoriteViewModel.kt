package com.example.weatherapp.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :ViewModel(){
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect(){
                if (it.isNullOrEmpty()){
                    Log.d("TAG", "Empty: favs ")
                }else{
                    _favList.value= it
                    Log.d("FAVS", "${favList.value}: ")
                }
            }
        }
    }

    fun insertFav(favorite: Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }
    fun deleteFav(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite) }
    fun getAllFavs() = viewModelScope.launch { repository.getFavorites() }

}

