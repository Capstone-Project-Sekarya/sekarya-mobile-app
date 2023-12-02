package com.android.sekarya_mobile_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.ActivityFormPortofolioBinding
import com.google.android.material.chip.Chip

class FormPortofolioActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormPortofolioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPortofolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Daftar tag yang tersedia
        val tagOptions = arrayOf("Tag1", "Tag2", "Tag3", "Tag4")

        // Buat adapter untuk AutoCompleteTextView
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tagOptions)
        binding.autoCompleteTextView.setAdapter(adapter)

        // Tambahkan listener untuk AutoCompleteTextView
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->

            val selectedTag = adapter.getItem(position).toString()

            if (binding.chipGroup.childCount < 3){
                addChip(selectedTag)
                binding.autoCompleteTextView.setText("") // Bersihkan teks setelah menambahkan chip
            } else {
                Toast.makeText(this, "melebihi batas maksimum tag", Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun addChip(tag: String) {
        val chip = Chip(this)
        chip.text = tag
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener {
            binding.chipGroup.removeView(chip)
        }
        binding.chipGroup.addView(chip)
    }




}