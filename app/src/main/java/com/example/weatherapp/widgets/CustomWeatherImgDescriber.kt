package com.example.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CustomWeatherImgDescriber(img: Painter) {
    Image(
        painter = img,
        contentDescription = "weather img",
        modifier = Modifier
            .size(50.dp)
    )
}