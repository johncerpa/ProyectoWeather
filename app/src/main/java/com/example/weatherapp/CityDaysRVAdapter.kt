package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.CityDay
import com.example.weatherapp.databinding.DayrowBinding

class CityDaysRVAdapter (
    private val mValue: List<CityDay>
) : RecyclerView.Adapter<CityDaysRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dayBinder : DayrowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.dayrow, parent, false
        )
        return ViewHolder(dayBinder)
    }

    override fun getItemCount(): Int = mValue.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValue[position]
        holder.mView.cityDay = item
        holder.mView.executePendingBindings()
    }

    fun updateData() { notifyDataSetChanged() }

    inner class ViewHolder(val mView: DayrowBinding) : RecyclerView.ViewHolder(mView.root)

}