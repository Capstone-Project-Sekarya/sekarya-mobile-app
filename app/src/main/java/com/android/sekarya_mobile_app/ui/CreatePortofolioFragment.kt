package com.android.sekarya_mobile_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.FragmentCreatePortofolioBinding

class CreatePortofolioFragment : Fragment() {

    private lateinit var binding: FragmentCreatePortofolioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePortofolioBinding.inflate(inflater , container , false)
        return binding.root
    }


    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        // Menambahkan listener ke button
        binding.btnContinueUpload.setOnClickListener {
            // Memanggil metode untuk berpindah ke activity target
            goToTargetActivity()
        }
    }

    private fun goToTargetActivity() {
        val intent = Intent(activity, FormPortofolioActivity::class.java)
        startActivity(intent)
    }

}