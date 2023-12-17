package com.android.sekarya_mobile_app.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.android.sekarya_mobile_app.databinding.ActivityPublicProfileBinding
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.ui.ViewModelFactory
import com.android.sekarya_mobile_app.ui.login.LogInActivity

class PublicProfileActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPublicProfileBinding

    private val authViewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }
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
            val age = binding.edAge.text.toString().toInt()
            val bio = binding.edBio.text.toString()
            val jobCategory = binding.edJobCategory.text.toString()

            when {
                username.isEmpty() -> {
                    binding.edUsername.error= "Kolom tidak boleh kosong"
                }
                age == null -> {
                    binding.edAge.error= "Kolom tidak boleh kosong"
                }
                bio.isEmpty() -> {
                    binding.edBio.error= "Kolom tidak boleh kosong"
                }
                jobCategory.isEmpty() -> {
                    binding.edJobCategory.error= "Kolom tidak boleh kosong"
                }

                else -> {
                    if (email != null && password != null && fullName != null && phoneNumber != null && gender != null && age != null && dateOfBirth != null) {
                        authViewModel.registerUser(username,email,password,fullName,phoneNumber,gender,age,jobCategory,dateOfBirth,bio)
                    }

                    Log.d("PublicProfileActivity","email : $email, password: $password, fullName: $fullName," +
                            " phoneNumber: $phoneNumber, dateOfBirth: $dateOfBirth, gender: $gender, username: $username, age: $age, bio: $bio")
                }

            }


        }

        authViewModel.registrationResult.observe(this) { result ->
            when(result) {
                is Response.Loading -> {

                }
                is Response.Success -> {
                    val intent = Intent(this, LogInActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                is Response.Error -> {
                    Toast.makeText(this,"Registrasi tidak berhasil!", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
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