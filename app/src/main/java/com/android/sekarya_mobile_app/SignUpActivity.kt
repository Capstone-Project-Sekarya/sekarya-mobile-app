package com.android.sekarya_mobile_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.sekarya_mobile_app.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toLogin()
    }

    fun toLogin(){
        binding.tvLoginRight.setOnClickListener(){
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}