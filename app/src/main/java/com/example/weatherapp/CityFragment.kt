package com.example.weatherapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.CityDay
import com.example.weatherapp.data.CityWeather
import com.example.weatherapp.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private lateinit var daysViewModel: DaysViewModel

    lateinit var city: CityWeather
    lateinit var mBinding: FragmentCityBinding

    var days = mutableListOf<CityDay>()
    private var daysList = mutableListOf<CityDay>()

    private var adapter : CityDaysRVAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)

        daysViewModel = ViewModelProvider(this).get(DaysViewModel::class.java)

        city = arguments!!.getParcelable("city")!!
        mBinding.city = city

        daysViewModel.addCityDays(city.cityName)

        daysViewModel.getCityDays().observe(viewLifecycleOwner, Observer { cityDaysResult ->
            run {
                daysList = cityDaysResult as MutableList<CityDay>

                days.clear()
                var i : Int = 1
                for (day in daysList) {
                    days.add(CityDay(
                        "Day $i",
                        day.temperature,
                        day.description,
                        day.iconUrl
                    ))
                    i++
                }
            }
            adapter!!.updateData()
        })

        adapter = CityDaysRVAdapter(days)

        mBinding.daysList.layoutManager = LinearLayoutManager(context)
        mBinding.daysList.adapter = adapter

        return mBinding.root
    }

}
