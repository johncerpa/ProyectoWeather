package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.CityWeather
import com.example.weatherapp.databinding.RowBinding

class CityRVAdapter (
    private val mValue: List<CityWeather>,
    private val mListener: onListInteraction
) : RecyclerView.Adapter<CityRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binder : RowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row, parent, false
        )

        return ViewHolder(binder)
    }

    override fun getItemCount(): Int = mValue.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValue[position]
        holder.mView.city = item
        holder.mView.executePendingBindings()

        holder.mView.cardView.setOnClickListener {
            mListener?.onListItemInteraction(item)
        }
    }

    fun updateData() { notifyDataSetChanged() }

    inner class ViewHolder(val mView: RowBinding) : RecyclerView.ViewHolder(mView.root)

    interface onListInteraction {
        fun onListItemInteraction(item: CityWeather?)
    }
}