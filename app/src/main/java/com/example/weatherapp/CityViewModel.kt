package com.example.weatherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.CityWeather
import com.example.weatherapp.data.CityWeatherSchema

class CityViewModel (application: Application) : AndroidViewModel(application) {

    private var cityDao : CityDao

    init {
        cityDao = CityDao.getInstance(this.getApplication())
    }

    fun addCities() {
        cityDao.addCities()
    }

    fun getCities(): MutableLiveData<List<CityWeather>> {
        return cityDao.getCities()
    }
}