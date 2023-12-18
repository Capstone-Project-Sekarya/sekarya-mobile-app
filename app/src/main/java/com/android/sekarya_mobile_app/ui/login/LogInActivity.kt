package com.android.sekarya_mobile_app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.android.sekarya_mobile_app.databinding.ActivityLogInBinding
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.ui.NavigationActivity
import com.android.sekarya_mobile_app.ui.ViewModelFactory
import com.android.sekarya_mobile_app.ui.register.AuthViewModel
import com.android.sekarya_mobile_app.ui.register.PublicProfileActivity
import com.android.sekarya_mobile_app.ui.register.SignUpActivity

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private val authViewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logIn()
        toSignUp()

        authViewModel.loginResult.observe(this) { result ->
            when(result) {
                is Response.Loading -> {

                }
                is Response.Success -> {
                    val intent = Intent(this, NavigationActivity::class.java)
                    Log.d("loginActivity","${result.data}")
                    startActivity(intent)
                }
                is Response.Error -> {
                    Toast.makeText(this,"Login tidak berhasil!", Toast.LENGTH_SHORT).show()
                }

                else -> {
                }
            }
        }

    }

    private fun toSignUp (){
        binding.tvRegisRight.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logIn (){
        binding.btnLogin.setOnClickListener(){
            val email = binding.edEmailLogin.text.toString()
            val password = binding.edPasswordLogin.text.toString()
            when {
                email.isEmpty() -> {
                    binding.edEmailLogin.error= "Kolom tidak boleh kosong"
                }
                password.isEmpty() -> {
                    binding.edPasswordLogin.error= "Kolom tidak boleh kosong"
                }

                else -> {
                    authViewModel.login(email,password)
                }
            }

        }
    }



}