package com.android.sekarya_mobile_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.Hire

class HireAdapter(private var hireList : ArrayList<Hire>) :
    RecyclerView.Adapter<HireAdapter.HireViewHolder>() {

    class HireViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgHire: ImageView = itemView.findViewById(R.id.img_card_popup)
        val tvArt: TextView = itemView.findViewById(R.id.tv_art)
        val tvPost: TextView = itemView.findViewById(R.id.tv_post)
        val tvArtTitle: TextView = itemView.findViewById(R.id.tv_art_title)
        val tvDeliver: TextView = itemView.findViewById(R.id.tv_deliver)
        val tvPortofolio: TextView = itemView.findViewById(R.id.tv_portofolio)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HireViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_hire, parent, false)
        return HireViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return hireList.size
    }

    override fun onBindViewHolder(holder: HireViewHolder, position: Int) {
        val currentItem = hireList[position]
        holder.imgHire.setImageResource(currentItem.imageHire)
        holder.tvArt.text = currentItem.tvArt
        holder.tvPost.text = currentItem.tvPost
        holder.tvArtTitle.text = currentItem.tvArtTitle
        holder.tvDeliver.text = currentItem.tvDeliver
        holder.tvPortofolio.text = currentItem.tvPortofolio
        holder.tvPrice.text = currentItem.tvPrice
    }
}