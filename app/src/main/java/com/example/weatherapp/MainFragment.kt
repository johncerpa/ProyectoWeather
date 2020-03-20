package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.CityWeather
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), CityRVAdapter.onListInteraction {

    private lateinit var viewModel: CityViewModel

    var cities = mutableListOf<CityWeather>()
    private var citiesList = mutableListOf<CityWeather>()
    private var adapter : CityRVAdapter? = null

    lateinit var navController : NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        viewModel.addCities()

        viewModel.getCities().observe(viewLifecycleOwner, Observer { citiesResult ->
            run {
                citiesList = citiesResult as MutableList<CityWeather>

                cities.clear()
                for (city in citiesList) {
                    cities.add(CityWeather(
                        city.name,
                        city.temperature,
                        city.description,
                        city.iconUrl,
                        city.cityName
                    ))
                }
            }
            adapter!!.updateData()
        })

        adapter = CityRVAdapter(cities, this)

        view.citiesList.layoutManager = LinearLayoutManager(context)
        view.citiesList.adapter = adapter

        return view
    }

    override fun onListItemInteraction(item: CityWeather?) {
        val bundle = bundleOf("city" to item)
        navController.navigate(R.id.action_mainFragment_to_cityFragment, bundle)
    }


}
