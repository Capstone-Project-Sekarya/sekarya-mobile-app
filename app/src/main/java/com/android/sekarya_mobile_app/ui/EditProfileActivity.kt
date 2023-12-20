package com.android.sekarya_mobile_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.ActivityEditProfileBinding
import com.android.sekarya_mobile_app.databinding.ActivityNavigationBinding
import com.android.sekarya_mobile_app.databinding.ActivityPublicProfileBinding
import com.android.sekarya_mobile_app.ui.register.InfoPersonalActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener(){
            btnBack()
        }

        binding.btnSave.setOnClickListener(){
            btnSave()
        }

    }

    private fun btnBack(){
        val intent = Intent(this, UserProfileFragment::class.java)
        startActivity(intent)
    }

    private fun btnSave(){
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
    }
}