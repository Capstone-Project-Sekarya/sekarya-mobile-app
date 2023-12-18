package com.android.sekarya_mobile_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.TagList

class TagAdapter(private val tagList : ArrayList<TagList>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_tag_home, parent, false)
        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentItem = tagList[position]
        holder.btnTag.text = currentItem.tag
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val btnTag: TextView = itemView.findViewById(R.id.btn_tag)

    }
}