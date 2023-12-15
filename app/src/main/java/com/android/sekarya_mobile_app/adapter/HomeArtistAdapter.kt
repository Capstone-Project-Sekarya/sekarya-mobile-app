package com.android.sekarya_mobile_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.HomeArtist
import de.hdodenhof.circleimageview.CircleImageView

class HomeArtistAdapter(private val artistList : ArrayList<HomeArtist>) : RecyclerView.Adapter<HomeArtistAdapter.HomeArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArtistViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_artist, parent, false)
        return HomeArtistViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    override fun onBindViewHolder(holder: HomeArtistViewHolder, position: Int) {
        val currentItem = artistList[position]
        holder.imageArtist.setImageResource(currentItem.ImageId)
        holder.tvName.text = currentItem.name
        holder.tvUserName.text = currentItem.userName
    }

    class HomeArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageArtist: CircleImageView = itemView.findViewById(R.id.img_artist_home)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_user_name)

    }
}