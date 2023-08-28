package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun CustomDivider (){
    Divider(
        thickness = 2.dp,
        color = colorResource(id = R.color.dark_purple),
        modifier = Modifier.padding(top = 8.dp)
    )
    Divider(
        thickness = 5.dp,
        color = colorResource(id = R.color.baby_blue),

    )
    Divider(
        thickness = 2.dp,
        color = colorResource(id = R.color.dark_purple),
        modifier = Modifier.padding( bottom = 8.dp)
    )
}