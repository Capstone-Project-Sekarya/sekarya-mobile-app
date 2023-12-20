package com.android.sekarya_mobile_app.ui.createArt

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import com.android.sekarya_mobile_app.MainActivity
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.custom_view.CustomDialog
import com.android.sekarya_mobile_app.databinding.ActivityFormPortofolioBinding
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.ui.NavigationActivity
import com.android.sekarya_mobile_app.ui.ViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import java.io.File
import java.io.Serializable

class FormPortofolioActivity : AppCompatActivity(), Serializable {

    private lateinit var binding : ActivityFormPortofolioBinding
    private lateinit var customDialog: CustomDialog

    private val createViewModel by viewModels<CreateViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPortofolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        show image
        val imageUriString = intent.getStringExtra("IMAGE_URI")
        val imageUri = Uri.parse(imageUriString)
        if (imageUri != null) {
            Glide.with(this)
                .load(imageUri)
                .into(binding.imageView)
        }

//        Daftar Tag
        val tagOptions = arrayOf("Ilustrator", "Karikatur", "Baner", "Logo")

//        Adapter untuk AutoCompleteTextView
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


//        upload data
        binding.btnContinueUpload.setOnClickListener(){
            val tittle =  binding.tiTittle.text.toString()
            val description = binding.descriptionInput.text.toString()
            val tags = binding.autoCompleteTextView.text.toString()
            val imageFile : File? = intent.getSerializableExtra("IMAGE_FILE") as? File
            Log.d("Image", "Selected image: $imageFile")
            Log.d("tittle", "tittle: $tittle")
            Log.d("description", "desc: $description")
            Log.d("tags", "tags: $tags")

            createViewModel.addArt(imageFile, tittle, tags,description)

            createViewModel.creatResult.observe(this) { result ->
                result.getContentIfNotHandled()?.let {
                    when (it) {
                        is Response.Loading -> {

                        }
                        is Response.Success -> {
                            Toast.makeText(this, "Berhasil upload gambar.", Toast.LENGTH_SHORT).show()
                            showCustomDialog("Upload Artwork Successful!","",R.drawable.human_art,false,true,true)
                            val intent = Intent(this, NavigationActivity::class.java)
                            startActivity(intent)
                        }
                        is Response.Error -> {
                            Toast.makeText(this, "Tidak berhasil upload gambar.", Toast.LENGTH_SHORT).show()
                        }

                        else -> {}
                    }

                }

            }

        }



//        cancel handler
        goToHome()
////        save handler
//        save()


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


//  save handler

//    private fun save(){
//        binding.btnSave.setOnClickListener{
//            val customDialog = CustomDialog(this)
//            customDialog.show()
//            customDialog.setImageResource(R.drawable.ic_detect_art)
//            customDialog.setTitle("Popup Title")
//            customDialog.setDescription("Popup Description")
//            customDialog.setLoadingIndicatorVisible(true)
//
////            customDialog.dismiss() -> popup dialog gone
//
//        }
//    }

    private fun goToHome(){
        binding.btnCencel.setOnClickListener{
            val intent = Intent(this, CreatePortofolioFragment::class.java)
            startActivity(intent)
        }

    }

    private fun showCustomDialog(
        title: String,
        description: String,
        imageResId: Int,
        showLoading: Boolean,
        cancelByTouch: Boolean,
        cancel : Boolean
    ){
        val customDialog = CustomDialog(this)
        customDialog.show()
        customDialog.setImageResource(imageResId)
        customDialog.setTitle(title)
        customDialog.setDescription(description)
        customDialog.setLoadingIndicatorVisible(showLoading)
        customDialog.setCanceledOnTouchOutside(cancelByTouch)
        customDialog.setCancelable(cancel)

    }



}