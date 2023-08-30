@file:Suppress("UNUSED_EXPRESSION")

package com.example.weatherapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isOnMainScreen: Boolean = true,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
){
    TopAppBar(
        title = { Text(
            text = title,
            fontWeight = FontWeight.SemiBold,

        ) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.dark_purple),
            titleContentColor = colorResource(id = R.color.egg),
            actionIconContentColor = colorResource(id = R.color.egg),
            navigationIconContentColor = colorResource(id = R.color.egg)
        ),
        actions = {
                  if(isOnMainScreen){
                      IconButton(onClick = { onAddActionClicked()}) {
                          Icon(imageVector =  Icons.Default.Search, contentDescription = "Search icon")
                      }
                      IconButton(onClick = { onButtonClicked() }) {
                          Icon(imageVector =  Icons.Rounded.MoreVert, contentDescription = "More icon")
                      }
                  }else{
                      Box{}
                  }
        },
        navigationIcon = {
            if(icon != null){
                Icon(imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable{
                        onButtonClicked()
                    }
                    )

            }
        }

    )


}