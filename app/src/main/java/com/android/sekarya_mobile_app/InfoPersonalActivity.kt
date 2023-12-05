package com.android.sekarya_mobile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.android.sekarya_mobile_app.databinding.ActivityInfoPersonalBinding

class InfoPersonalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPersonalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        back()
        spinnerGender()
        skip()
        next()

    }


    private fun spinnerGender(){
        //        value spinner
        val gender = arrayOf("Male","Female")
//        adapter untuk spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = binding.spGender
        spinner.adapter = adapter
    }

    private fun back(){

        binding.ivBack.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun skip(){
        binding.btnSkip.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun next(){
        binding.btnContinue.setOnClickListener(){
            val intent = Intent(this, PublicProfileActivity::class.java)
            startActivity(intent)
        }

    }

}