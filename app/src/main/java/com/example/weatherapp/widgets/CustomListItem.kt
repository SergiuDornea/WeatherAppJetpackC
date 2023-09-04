package com.example.weatherapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.screens.favorite.FavoriteViewModel


@Composable
fun CustomListITem(favorite: Favorite, favoriteViewModel: FavoriteViewModel, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable{
                navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}")
            }
            .clip(RoundedCornerShape(topEnd = 53.dp, bottomStart = 53.dp)),
        shape =RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_purple),
            contentColor = colorResource(id = R.color.egg)
        )
    ){
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = favorite.city,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )


            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(50.dp),
                color = colorResource(id = R.color.light_purple),
                contentColor = colorResource(id = R.color.egg),

            ) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = favorite.country.trim(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                }
                
            }

            IconButton(
                onClick = {  favoriteViewModel.deleteFav(favorite)}

            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete favorite",
                    modifier = Modifier
                        .size(30.dp)

                )
                
            }

        }


    }

}