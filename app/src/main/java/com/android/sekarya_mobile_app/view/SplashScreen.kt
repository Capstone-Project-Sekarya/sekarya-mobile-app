package com.android.sekarya_mobile_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.ActivitySplashScreenBinding
import com.android.sekarya_mobile_app.view.onboarding.OnboardingActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    private val SPLASH_DURATION: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        showLoadingIndicator()


        Handler().postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DURATION)

        Log.d("SplashScreen", "ProgressBar visibility: ${binding.loadingIndicator.visibility}")

    }
//
//    private fun showLoadingIndicator() {
//        binding.loadingIndicator.visibility = View.VISIBLE
//    }
//
//    private fun hideLoadingIndicator() {
//        binding.loadingIndicator.visibility = View.GONE
//    }
}