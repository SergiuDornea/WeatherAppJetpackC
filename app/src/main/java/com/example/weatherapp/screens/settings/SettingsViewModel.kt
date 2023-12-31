package com.example.weatherapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Unit
import com.example.weatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor( private val repository: WeatherDbRepository) : ViewModel(){
    private val _unitsList = MutableStateFlow<List<Unit>>(emptyList())
    val unitsList = _unitsList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getUnits().distinctUntilChanged().collect{
                if (it.isNullOrEmpty()){
                    Log.d("TAG", "Empty: units ")
                }else{
                    _unitsList.value= it
                    Log.d("UNIT", "${unitsList.value}: ")
                }
            }
        }
    }

    fun insertUnit(unit:Unit) = viewModelScope.launch { repository.insertUnit(unit) }
    fun deleteUnit( unit:Unit) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }
    fun getAllUnits() = viewModelScope.launch { repository.getUnits() }
}