package com.example.weatherapp.screens.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.widgets.CustomListITem
import com.example.weatherapp.widgets.WeatherAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherFavoriteScreen(navController: NavController, favoriteViewModel: FavoriteViewModel = hiltViewModel()) {
    val favoriteCities =favoriteViewModel.favList.collectAsState().value
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorite cities",
            navController = navController,
            isOnMainScreen = false,
            icon = Icons.Default.ArrowBack
        ){
            // return when arrow clicked
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.light_purple)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 80.dp)
            ) {
                items(items = favoriteCities) {
                    CustomListITem(
                        favorite = it,
                        favoriteViewModel = favoriteViewModel,
                        navController = navController
                    )
                }
            }
            
        }
        
    }

}