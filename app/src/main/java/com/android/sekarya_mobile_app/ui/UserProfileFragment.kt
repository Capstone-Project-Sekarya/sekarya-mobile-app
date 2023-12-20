package com.android.sekarya_mobile_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.FragmentUserProfileBinding
import com.android.sekarya_mobile_app.model.Hire
import com.android.sekarya_mobile_app.model.Profile
import com.android.sekarya_mobile_app.ui.adapter.HireAdapter
import com.android.sekarya_mobile_app.ui.adapter.ProfileAdapter

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var profileList: ArrayList<Profile>

    lateinit var imgPorto: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setDataPorto()
        setRvPorto()

        binding.btnEditProfile.setOnClickListener(){
            editProfile()
        }

    }

    private fun setRvPorto(){
        recyclerView = requireView().findViewById(R.id.rv_card_profile)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.setHasFixedSize(true)
        profileAdapter = ProfileAdapter(profileList)
        recyclerView.adapter = profileAdapter
    }

    private fun setDataPorto(){
        profileList = arrayListOf<Profile>()

        imgPorto = arrayOf(
            R.drawable.card_image,
            R.drawable.card_image_2,
            R.drawable.wanderer,
            R.drawable.frieren,
            R.drawable.ryo,
            R.drawable.card_image,
            R.drawable.card_image_2,
            R.drawable.wanderer,
            R.drawable.frieren,
            R.drawable.ryo
        )

        for (i in imgPorto.indices){
            val porto = Profile(imgPorto[i])
            profileList.add(porto)
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


