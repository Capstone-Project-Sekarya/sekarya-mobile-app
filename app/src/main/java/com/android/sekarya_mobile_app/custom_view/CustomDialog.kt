package com.android.sekarya_mobile_app.custom_view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.android.sekarya_mobile_app.R




class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var loadingIndicator: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_dialog)

        // Inisialisasi komponen tampilan
        imageView = findViewById(R.id.iv_dialog)
        titleTextView = findViewById(R.id.tv_title)
        descriptionTextView = findViewById(R.id.tv_description)
        loadingIndicator = findViewById(R.id.loadingIndicator)

        // Atur latar belakang dialog
        window?.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.color.transparent))

        // Mengatur properti dialog
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    // Metode untuk mengatur gambar
    fun setImageResource(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    // Metode untuk mengatur judul
    fun setTitle(title: String) {
        titleTextView.text = title
    }

    // Metode untuk mengatur deskripsi
    fun setDescription(description: String) {
        descriptionTextView.text = description
    }

    // Metode untuk menampilkan/menyembunyikan indikator loading
    fun setLoadingIndicatorVisible(visible: Boolean) {
        loadingIndicator.visibility = if (visible) View.VISIBLE else View.GONE
    }
}