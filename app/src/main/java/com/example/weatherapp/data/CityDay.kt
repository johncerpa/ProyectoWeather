package com.example.weatherapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CityDay (
  val dayNumber: String,
  val temperature: String,
  val description: String,
  val iconUrl: String
) : Parcelable {}