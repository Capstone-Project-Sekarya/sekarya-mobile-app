package com.android.sekarya_mobile_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.Profile

class ProfileAdapter(private var profileList : ArrayList<Profile>) :
    RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgPorto: ImageView = itemView.findViewById(R.id.img_porto)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_porto, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = profileList[position]
        holder.imgPorto.setImageResource(currentItem.imagePorto)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
}