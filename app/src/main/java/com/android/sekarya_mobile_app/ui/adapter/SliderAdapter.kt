package com.android.sekarya_mobile_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.Slider

class SliderAdapter(private val sliderList : ArrayList<Slider>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_banner, parent, false)
        return SliderViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sliderList.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val currentItem = sliderList[position]
        holder.imageSlider.setImageResource(currentItem.imageId)
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageSlider: ImageView = itemView.findViewById(R.id.img_banner)
    }
}