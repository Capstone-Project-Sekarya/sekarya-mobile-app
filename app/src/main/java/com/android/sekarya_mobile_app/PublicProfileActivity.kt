package com.android.sekarya_mobile_app

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.android.sekarya_mobile_app.databinding.ActivityInfoPersonalBinding
import com.android.sekarya_mobile_app.databinding.ActivityPublicProfileBinding

class PublicProfileActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPublicProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        spinnerAdapter()
        back()


    }


    private fun spinnerAdapter(){

//        value spinner
        val pronouns = arrayOf("English","Indonesia")
//        adapter untuk spinner
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, pronouns)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = binding.spPronouns
        spinner.adapter = adapter
    }

    private fun back(){

        binding.ivBack.setOnClickListener(){
            val intent = Intent(this, InfoPersonalActivity::class.java)
            startActivity(intent)
        }

    }

}