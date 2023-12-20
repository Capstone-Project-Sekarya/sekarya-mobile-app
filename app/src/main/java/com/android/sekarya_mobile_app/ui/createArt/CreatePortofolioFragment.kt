package com.android.sekarya_mobile_app.ui.createArt

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.custom_view.CustomDialog
import com.android.sekarya_mobile_app.databinding.FragmentCreatePortofolioBinding
import com.android.sekarya_mobile_app.model.response.Response
import com.android.sekarya_mobile_app.ui.NavigationActivity
import com.android.sekarya_mobile_app.ui.ViewModelFactory
import com.android.sekarya_mobile_app.ui.login.LogInActivity
import com.android.sekarya_mobile_app.ui.register.AuthViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*

const val BUFFER_SIZE: Int = 1024 * 2
class CreatePortofolioFragment : Fragment() {

    private lateinit var binding: FragmentCreatePortofolioBinding

    lateinit var customDialog: CustomDialog

    private var selectedImageUri: Uri? = null
    private var imageFile: File? = null


    private val predictArtViewModel by viewModels<PredictArtViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePortofolioBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.btnUploadArt.setOnClickListener{
            openImagePicker()
        }


        binding.btnSave.setOnClickListener{
            predictArtViewModel.predictArt(imageFile)
        }

        binding.btnCencel.setOnClickListener(){
            val intent = Intent(activity, NavigationActivity::class.java)
            startActivity(intent)
        }


//        predict art
        predictArtViewModel.predictResult.observe(requireActivity()) { result ->
            result.getContentIfNotHandled()?.let {
                when (it) {

                    is Response.Loading -> {

                        showCustomDialog("Detect Your Art","Please wait...\n" +
                                "We verify your amazing artwork",R.drawable.ic_detect_art,true,true,true, 3000)

                    }
                    is Response.Success -> {


                        if ( it.data.predicted_class == "NON_AI_GENERATED" ){
                            Toast.makeText(requireContext(), "${it.data.predicted_class}", Toast.LENGTH_SHORT).show()
                            Log.d("Image", "Selected image: $selectedImageUri")
                            binding.btnContinueUpload.setOnClickListener(){
                                val intent = Intent(activity, FormPortofolioActivity::class.java)
                                intent.putExtra("IMAGE_FILE", imageFile)
                                intent.putExtra("IMAGE_URI", selectedImageUri.toString())
                                startActivity(intent)
                            }
                            Handler().postDelayed({
                                showCustomDialog("Your art was Made by Human","Click Continue\n" +
                                        "for directed you to nextpag",R.drawable.human_art,false,true,true, 6000)
                            }, 2000)

                        } else if (it.data.predicted_class == "AI_GENERATED"){
                            Toast.makeText(requireContext(), "${it.data.predicted_class}.", Toast.LENGTH_SHORT).show()
                            binding.btnContinueUpload.setOnClickListener(){
                                Toast.makeText(requireContext(), "Upload original gambar", Toast.LENGTH_SHORT).show()
                            }
                            showCustomDialog("AI Generated Detected","Please upload your human art work\n" +
                                    "We do not accepted AI Generated Art",R.drawable.ic_rejected,false,true,true,3000)
                        }
                        else{
                            Toast.makeText(requireContext(), "Bad Request.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Response.Error -> {
                        Toast.makeText(requireContext(), "Tidak berhasil upload gambar.", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }

            }

        }

    }


    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the result of the image selection
        if (uri != null) {
            selectedImageUri = uri
            // Lakukan sesuatu dengan URI gambar yang dipilih
            binding.tvUploadUrl.text = uri.toString()
            // Contohnya, tampilkan gambar di ImageView atau lakukan upload ke server
            imageFile = requireActivity().getFileFromUri(selectedImageUri)
        }
    }
    private fun openImagePicker() {
        pickImage.launch("image/*")
    }


    fun Context.getFileFromUri(contentUri: Uri?): File? {
        val fileName: String = getFileName(contentUri) ?: ""
        Log.d("getfileuri", "FILE NAME FROM URI $fileName")
        Log.d("getfileuri", "FILE NAME FROM URI ${contentUri?.path}")

        val dir = File(
            requireContext().externalCacheDir.toString()
        )
        if (!dir.exists()) {
            dir.mkdirs()
        }
        if (!TextUtils.isEmpty(fileName)) {
            val copyFile = File(dir.toString() + File.separator + fileName)
            copy(this, contentUri, copyFile)
            return copyFile
        }
        return null
    }

    private fun getFileName(uri: Uri?): String? {
        if (uri == null) return null
        var fileName: String? = null
        val path = uri.path
        val cut = path!!.lastIndexOf('/')
        if (cut != -1) {
            fileName = path.substring(cut + 1)
        }
        return "$fileName.jpg"
    }

    private fun copy(context: Context, srcUri: Uri?, dstFile: File?) {
        try {
            val inputStream = context.contentResolver.openInputStream(srcUri!!) ?: return
            val outputStream: OutputStream = FileOutputStream(dstFile)
            copyStream(inputStream, outputStream)
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(java.lang.Exception::class, IOException::class)
    private fun copyStream(input: InputStream?, output: OutputStream?): Int {
        val buffer = ByteArray(BUFFER_SIZE)
        val `in` = BufferedInputStream(input, BUFFER_SIZE)
        val out = BufferedOutputStream(output, BUFFER_SIZE)
        var count = 0
        var n: Int
        try {
            while (`in`.read(buffer, 0, BUFFER_SIZE).also { n = it } != -1) {
                out.write(buffer, 0, n)
                count += n
            }
            out.flush()
        } finally {
            try {
                out.close()
            } catch (e: IOException) {
                Log.d("", e.toString())
            }
            try {
                `in`.close()
            } catch (e: IOException) {
                Log.d("", e.toString())
            }
        }
        return count
    }


    private fun showCustomDialog(
        title: String,
        description: String,
        imageResId: Int,
        showLoading: Boolean,
        cancelByTouch: Boolean,
        cancel : Boolean,
        delay : Long
    ){
        val customDialog = CustomDialog(requireContext())
        customDialog.show()
        customDialog.setImageResource(imageResId)
        customDialog.setTitle(title)
        customDialog.setDescription(description)
        customDialog.setLoadingIndicatorVisible(showLoading)
        customDialog.setCanceledOnTouchOutside(cancelByTouch)
        customDialog.setCancelable(cancel)
        customDialog.showWithAutoDismiss(delay)

    }






}