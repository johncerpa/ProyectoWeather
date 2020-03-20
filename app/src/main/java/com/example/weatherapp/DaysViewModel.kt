package com.example.weatherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.CityDay

class DaysViewModel (application: Application) : AndroidViewModel(application) {

    private var daysDao : DaysDao

    init {
        daysDao = DaysDao.getInstance(this.getApplication())
    }

    fun addCityDays(city : String) {
        daysDao.addCityDays(city)
    }

    fun getCityDays(): MutableLiveData<List<CityDay>> {
        return daysDao.getCityDays()
    }
}