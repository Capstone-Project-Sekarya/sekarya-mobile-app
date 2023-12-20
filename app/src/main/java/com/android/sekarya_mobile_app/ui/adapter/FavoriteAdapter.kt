package com.android.sekarya_mobile_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.Favorite

class FavoriteAdapter(private var favList : ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgFav: ImageView = itemView.findViewById(R.id.imageView2)
        val imgUserFav: ImageView = itemView.findViewById(R.id.img_user_fav)
        val tvArt: TextView = itemView.findViewById(R.id.tv_art_fav)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.favorite_cart, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = favList[position]
        holder.imgFav.setImageResource(currentItem.imageFav)
        holder.imgUserFav.setImageResource(currentItem.imageUser)
        holder.tvArt.text = currentItem.tvArt
    }
}