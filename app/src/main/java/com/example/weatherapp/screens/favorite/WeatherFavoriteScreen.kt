package com.example.weatherapp.screens.favorite

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.widgets.CustomListITem

@Preview
@Composable
fun FavoriteScreen() {
    val favoriteCities =listOf("Cluj", "Timisoara", "Oradea", "Bucharest")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 15.dp)
    ){
        items(items = favoriteCities){
            CustomListITem(it)
        }
    }


}