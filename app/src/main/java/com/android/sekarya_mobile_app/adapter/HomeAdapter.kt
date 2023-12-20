package com.android.sekarya_mobile_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.bumptech.glide.Glide
import com.android.sekarya_mobile_app.model.response.AllArtResponse

class HomeAdapter(private var context: Context,private var cardList : List<AllArtResponse>)
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgArt = itemView.findViewById<ImageView>(R.id.img_card_home)
        var imgUser = itemView.findViewById<ImageView>(R.id.img_user)
        var artName = itemView.findViewById<TextView>(R.id.tv_art_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_cart, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Glide.with(context).load(cardList[position].artPhoto).into(holder.imgArt)
        Glide.with(context).load(cardList[position].photoProfile).into(holder.imgUser)
        holder.artName.text = cardList[position].artName
    }

    override fun getItemCount(): Int {
        return cardList.count()
    }
}