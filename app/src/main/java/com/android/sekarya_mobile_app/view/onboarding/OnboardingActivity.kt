package com.android.sekarya_mobile_app.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.model.OnboardingPage
import com.android.sekarya_mobile_app.view.LogInActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager2
    private lateinit var onboardingAdapter: OnBoardingAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var nextButton: Button


    private val onboardingPages = listOf(
        OnboardingPage(R.drawable.image1, "Title 1", "Description 1"),
        OnboardingPage(R.drawable.image2, "Title 2", "Description 2"),
        OnboardingPage(R.drawable.image3, "Title 3", "Description 3")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        onboardingAdapter = OnBoardingAdapter(onboardingPages)
        viewPager.adapter = onboardingAdapter

        tabLayout = findViewById(R.id.tabLayout)
        indicatorLayout = findViewById(R.id.indicatorLayout)
        nextButton = findViewById(R.id.nextButton)

        // Inisialisasi dan menghubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // Menangani perubahan halaman untuk memperbarui indikator bulat
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
                updateButtonVisibility(position)
            }
        })

        // Menetapkan jumlah indikator sesuai dengan jumlah halaman
        for (i in 0 until onboardingAdapter.itemCount) {
            addIndicator()
        }

        // Menetapkan listener untuk tombol Next
        nextButton.setOnClickListener {
            if (viewPager.currentItem < onboardingAdapter.itemCount - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                // Jika di halaman terakhir, lakukan tindakan sesuai kebutuhan aplikasi
                startNextActivity()
            }
        }

        // Menetapkan keadaan awal tombol Next
        updateButtonVisibility(viewPager.currentItem)
    }

    private fun updateIndicators(position: Int) {
        // Menetapkan semua indikator ke non-aktif
        for (i in 0 until indicatorLayout.childCount) {
            val indicator = indicatorLayout.getChildAt(i) as ImageView
            indicator.setImageResource(R.drawable.ic_indicator_inactive)
        }

        // Menetapkan indikator yang sesuai dengan halaman aktif ke aktif
        val activeIndicator = indicatorLayout.getChildAt(position) as ImageView
        activeIndicator.setImageResource(R.drawable.ic_indicator_active)
    }


    private fun addIndicator() {
        // Menambahkan indikator bulat ke dalam layout
        val indicator = ImageView(this)
        indicator.setImageResource(R.drawable.ic_indicator_inactive)

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        indicator.layoutParams = layoutParams

        indicatorLayout.addView(indicator)
    }
    private fun updateButtonVisibility(position: Int) {
        // Menentukan apakah tombol Next harus terlihat atau tidak
        nextButton.visibility = if (position == onboardingAdapter.itemCount - 1) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun startNextActivity() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
        finish() // Opsional, untuk menutup aktivitas saat ini setelah berpindah ke aktivitas berikutnya
    }

}