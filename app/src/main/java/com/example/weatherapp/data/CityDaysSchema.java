package com.example.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityDaysSchema {

    public static ArrayList<CityDay> getCityDays(JSONObject response) {
        ArrayList<CityDay> cityDays = new ArrayList<>();

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i = i + 6) {

                JSONObject obj = list.getJSONObject(i);

                String temperature = obj.getJSONObject("main").getString("temp") + " °C";
                String description = obj.getJSONArray("weather").getJSONObject(0).getString("main");

                String icon = obj.getJSONArray("weather").getJSONObject(0).getString("icon");
                String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";

                cityDays.add(new CityDay("Day " + (i+1), temperature, description, iconUrl));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cityDays;
    }
}
