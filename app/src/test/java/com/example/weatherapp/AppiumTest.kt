package com.example.weatherapp

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.weatherapp.navigation.WeatherNavigation
import com.example.weatherapp.screens.main.WeatherMainScreen
import org.junit.Rule
import org.junit.Test




class AppiumTest {

    @get:Rule
    val rule = createComposeRule()

    @Test // verifica daca avem afisat numele orasului
    fun test1() {
        rule.setContent { WeatherNavigation() }
        }
}