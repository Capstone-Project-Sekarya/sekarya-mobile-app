package com.android.sekarya_mobile_app.view.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.OnboardingPage

class OnBoardingAdapter(private val onboardingPages: List<OnboardingPage>) : RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder>() {


    inner class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingAdapter.OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_screen, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingAdapter.OnboardingViewHolder, position: Int) {
        val onboardingPage = onboardingPages[position]
        holder.imageView.setImageResource(onboardingPage.imageResId)
        holder.textViewTitle.text = onboardingPage.title
        holder.textViewDescription.text = onboardingPage.description
    }

    override fun getItemCount(): Int {
        return onboardingPages.size
    }
}