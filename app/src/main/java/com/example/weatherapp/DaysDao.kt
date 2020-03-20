package com.example.weatherapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.example.weatherapp.data.CityDay
import com.example.weatherapp.data.CityDaysSchema
import org.json.JSONObject

class DaysDao private constructor(var context: Context) {

    private val cityDays = MutableLiveData<List<CityDay>>()
    private val cityDaysList= mutableListOf<CityDay>()

    private var queue : RequestQueue

    init {
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object {
        @Volatile
        private var INSTANCE : DaysDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DaysDao(context).also {INSTANCE = it}
            }
    }

    fun addCityDays(city: String) {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObject(city))
    }

    fun getCityDays() = cityDays

    fun getJsonObject(city: String) : JsonObjectRequest {
        val apiKey = "201608555f0414834a093a4730bac310"
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=$city&units=metric&appid=$apiKey"

        return JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response -> parseObjectG(response) },
            Response.ErrorListener { Log.d("Error", "Nojoda") }
        )
    }

    private fun parseObjectG(response: JSONObject) {
        var list = CityDaysSchema.getCityDays(response)

        cityDaysList.clear()
        for (i in 0 until list.size) {
            val cityDay = list[i]
            cityDaysList.add(cityDay)
        }

        cityDays.value = cityDaysList
    }


}