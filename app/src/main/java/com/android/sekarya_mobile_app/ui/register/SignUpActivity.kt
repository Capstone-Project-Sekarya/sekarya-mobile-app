package com.android.sekarya_mobile_app.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.sekarya_mobile_app.ui.login.LogInActivity
import com.android.sekarya_mobile_app.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toLogin()
        backButton()
        signUpButton()
    }

    fun toLogin(){
        binding.tvSignIn.setOnClickListener(){
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    fun backButton(){
        binding.ivBack.setOnClickListener(){
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    fun signUpButton(){
        binding.btnSignUp.setOnClickListener(){
            val email = binding.edEmailRegister.text.toString()
            val password = binding.edPasswordRegister.text.toString()
            when{
                email.isEmpty() -> {
                    binding.edEmailRegister.error = "Kolom tidak boleh kosong"
                }
                password.isEmpty() -> {
                    binding.edPasswordRegister.error = "Kolom tidak boleh kosong"
                }
                else -> {
                    val PublicIntent = Intent(this, InfoPersonalActivity::class.java)
                    PublicIntent.putExtra("email", email)
                    PublicIntent.putExtra("password", password)
//            val InfoPersonalIntent = Intent(this, InfoPersonalActivity::class.java)
                    startActivity(PublicIntent)
                }
            }
        }
    }
}