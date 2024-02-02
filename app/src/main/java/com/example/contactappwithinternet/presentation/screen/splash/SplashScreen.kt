package com.example.contactappwithinternet.presentation.screen.splash

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.contactappwithinternet.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToLogInUserScreen())
        }

        requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
    }

}