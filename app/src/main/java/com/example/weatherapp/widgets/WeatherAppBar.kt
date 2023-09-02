@file:Suppress("UNUSED_EXPRESSION")

package com.example.weatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
    val showDialog = remember{
        mutableStateOf(false)
    }

    if(showDialog.value){
        ShowDropDownSettingsMenu(showDialog = showDialog, navController = navController)
    }
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
                      IconButton(onClick = {
                          onButtonClicked()
                          showDialog.value = true
                      }) {
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

@Composable
fun ShowDropDownSettingsMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf("Favorites", "About", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier
                .width(140.dp)
                .background(colorResource(id = R.color.egg)),
            onDismissRequest = { expanded = false }) {
                items.forEachIndexed{index, text ->
                     DropdownMenuItem(
                         text = { Text(text = text)},
                         colors = MenuDefaults.itemColors(
                             textColor = colorResource(id = R.color.dark_purple),
                             leadingIconColor= colorResource(id = R.color.dark_purple)
                             ),
                         leadingIcon = {
                             Icon(imageVector = when(text){
                                 "Favorites" -> Icons.Default.FavoriteBorder
                                 "About" -> Icons.Default.Info
                                 "Settings" -> Icons.Default.Settings
                                 else -> Icons.Default.Settings },
                                 contentDescription = null
                                 )
                         },
                         onClick = { /*TODO*/ })
                }
        }
    }
}


//
//
