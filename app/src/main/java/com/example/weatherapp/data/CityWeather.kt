package com.example.weatherapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityWeather (
    val name: String,
    val temperature: String,
    val description: String,
    val iconUrl: String,
    val cityName: String
) : Parcelable {}