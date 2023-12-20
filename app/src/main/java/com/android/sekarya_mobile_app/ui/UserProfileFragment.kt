package com.android.sekarya_mobile_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.FragmentCreatePortofolioBinding
import com.android.sekarya_mobile_app.databinding.FragmentUserProfileBinding
import com.android.sekarya_mobile_app.ui.createArt.FormPortofolioActivity

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnEditProfile.setOnClickListener(){
            editProfile()
        }

    }

//    setting page
    private fun editProfile (){
        val intent = Intent(activity, EditProfileActivity::class.java)
        startActivity(intent)
    }

    private fun addPost(){
        val intent = Intent(activity, AddPostActivity::class.java)
        startActivity(intent)
    }



}


