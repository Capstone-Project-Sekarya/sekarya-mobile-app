package com.android.sekarya_mobile_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.ActivityCreatePortofolioBinding

class CreatePortofolioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePortofolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePortofolioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnContinueUpload.setOnClickListener{
            val intent = Intent(this@CreatePortofolioActivity, FormPortofolioActivity::class.java)
            startActivity(intent)
        }

    }
}