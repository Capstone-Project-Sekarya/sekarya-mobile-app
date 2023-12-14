package com.android.sekarya_mobile_app.ui.register

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.android.sekarya_mobile_app.databinding.ActivityPublicProfileBinding

class PublicProfileActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPublicProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val fullName = intent.getStringExtra("fullName")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val dateOfBirth = intent.getStringExtra("dateOfBirth")
        val gender = intent.getStringExtra("gender")

        binding.btnFinish.setOnClickListener(){
            val username = binding.edUsername.text.toString()
            val age = binding.edAge.text.toString()
            val bio = binding.edBio.text.toString()

            Log.d("PublicProfileActivity","email : $email, password: $password, fullName: $fullName," +
                    " phoneNumber: $phoneNumber, dateOfBirth: $dateOfBirth, gender: $gender, username: $username, age: $age, bio: $bio")
        }


        btnBack()


    }


//    private fun spinnerAdapter(){
//
////        value spinner
//        val pronouns = arrayOf("English","Indonesia")
////        adapter untuk spinner
//        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, pronouns)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        val spinner: Spinner = binding.spPronouns
//        spinner.adapter = adapter
//    }

    private fun btnBack(){

        binding.ivBack.setOnClickListener(){
            val intent = Intent(this, InfoPersonalActivity::class.java)
            startActivity(intent)
        }

    }


}