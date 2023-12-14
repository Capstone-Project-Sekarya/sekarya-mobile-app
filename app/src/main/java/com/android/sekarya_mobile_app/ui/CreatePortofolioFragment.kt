package com.android.sekarya_mobile_app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.android.sekarya_mobile_app.custom_view.CustomDialog
import com.android.sekarya_mobile_app.databinding.FragmentCreatePortofolioBinding

class CreatePortofolioFragment : Fragment() {

    private lateinit var binding: FragmentCreatePortofolioBinding

    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePortofolioBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        
        binding.btnContinueUpload.setOnClickListener {
            nextToFormPortofolio()
        }

        binding.btnUploadArt.setOnClickListener{
            openImagePicker()
        }

        binding.btnContinueUpload.setOnClickListener{
            goToContinue(selectedImageUri)
        }

    }

    private fun nextToFormPortofolio() {
        val intent = Intent(activity, FormPortofolioActivity::class.java)
        startActivity(intent)
    }


    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the result of the image selection
        if (uri != null) {
            selectedImageUri = uri
            // Lakukan sesuatu dengan URI gambar yang dipilih
            binding.tvUploadUrl.text = uri.toString()
            // Contohnya, tampilkan gambar di ImageView atau lakukan upload ke server
        }
    }
    private fun openImagePicker() {
        // Membuka pemilih gambar
        pickImage.launch("image/*")
    }

//    mengirim gambar ke activity form
    private fun goToContinue(imageUrl: Uri?) {
    val intent = Intent(activity, FormPortofolioActivity::class.java)
    intent.putExtra("IMAGE_URL", imageUrl)
    startActivity(intent)
    }

}