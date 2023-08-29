package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Preview
@Composable
fun CustomListITem(data: String = "dummy data") {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(topEnd = 53.dp, bottomStart = 53.dp)),
        shape =RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_purple),
            contentColor = colorResource(id = R.color.egg)
        )
    ){
        Row(
            modifier = Modifier.padding(top = 30.dp, start = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = data,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }


    }

}