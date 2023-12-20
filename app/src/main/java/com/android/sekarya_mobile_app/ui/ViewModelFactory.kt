package com.android.sekarya_mobile_app.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.sekarya_mobile_app.data.repository.ArtRepository
import com.android.sekarya_mobile_app.data.repository.UserRepository
import com.android.sekarya_mobile_app.di.Injection
import com.android.sekarya_mobile_app.ui.createArt.CreateViewModel
import com.android.sekarya_mobile_app.ui.home.HomeViewModel
import com.android.sekarya_mobile_app.ui.register.AuthViewModel

class ViewModelFactory (
    private val repository: UserRepository,
    private val artRepository: ArtRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CreateViewModel::class.java) -> {
                CreateViewModel(artRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(
                    Injection.userRepository(context),
                    Injection.artRepository(context)
                )
            }.also { INSTANCE = it }
    }
}