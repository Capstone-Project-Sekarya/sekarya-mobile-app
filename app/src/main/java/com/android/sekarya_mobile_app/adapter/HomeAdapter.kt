package com.android.sekarya_mobile_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.HomeCard

class HomeAdapter(private val cardList : ArrayList<HomeCard>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_cart, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = cardList[position]
        holder.imageCard.setImageResource(currentItem.imageId)
        holder.imageBookmark.setImageResource(currentItem.imgBookmark)
        holder.imageUser.setImageResource(currentItem.imgUser)
        holder.tvUserName.text = currentItem.userName
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageCard: ImageView = itemView.findViewById(R.id.img_card_home)
        val imageBookmark: ImageView = itemView.findViewById(R.id.btn_bookmark)
        val imageUser: ImageView = itemView.findViewById(R.id.img_user)
        val tvUserName: TextView = itemView.findViewById(R.id.user)

    }
}