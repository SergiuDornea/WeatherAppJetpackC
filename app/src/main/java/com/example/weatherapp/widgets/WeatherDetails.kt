package com.example.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.weatherapp.R


@Composable
fun WeatherDetails(img: Painter , description: String){
    Row(
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = img,
            contentDescription = "weather description img",
            modifier = Modifier
                .size(30.dp)
        )
        Text(
            text = description,
            fontSize = 16.sp,
            color = colorResource(id = R.color.egg)

        )
//        Spacer(modifier = Modifier.padding(end = space))
        
    }

}

