package com.android.sekarya_mobile_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.android.sekarya_mobile_app.R
import com.android.sekarya_mobile_app.databinding.ActivityNavigationBinding
import com.android.sekarya_mobile_app.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callFragment(HomeFragment())

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> callFragment(HomeFragment())
                R.id.favorite -> callFragment(FavoriteFragment())
                R.id.create -> callFragment(CreatePortofolioFragment())
                R.id.hire -> callFragment(HireFragment())
                R.id.profile -> callFragment(UserProfileFragment())

                else -> {

                }
            }

            true
        }

    }

    private fun callFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}