package com.example.weatherapp.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityWeatherSchema {

    public static ArrayList<CityWeather> getCities(JSONObject response) {
        ArrayList<CityWeather> cities = new ArrayList<>();

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {

                JSONObject obj = list.getJSONObject(i);

                String country = obj.getJSONObject("sys").getString("country");
                String cityNameCountry = obj.getString("name") + ", " + country;
                String cityName = obj.getString("name");
                String temperature = obj.getJSONObject("main").getString("temp") + " °C";
                String description = obj.getJSONArray("weather").getJSONObject(0).getString("main");
                String icon = obj.getJSONArray("weather").getJSONObject(0).getString("icon");

                String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";

                cities.add(new CityWeather(cityNameCountry, temperature, description, iconUrl, cityName));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cities;
    }

}
