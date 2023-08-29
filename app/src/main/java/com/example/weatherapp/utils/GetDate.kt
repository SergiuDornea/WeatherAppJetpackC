package com.example.weatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getDateTime(unix: Int, pattern : String): String {
        val sdf = SimpleDateFormat(pattern)  // "EEE, MMM d"  "hh:mm aa"
        val unformattedDate = Date(unix.toLong() * 1000)
        return sdf.format(unformattedDate)
}

