package com.example.contactappwithinternet.presentation.ui.info

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactappwithinternet.R
import com.example.contactappwithinternet.databinding.ScreenInfoBinding

class InfoScreen : Fragment(R.layout.screen_info) {

    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val navArgs: InfoScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val phoneNumber = navArgs.phoneNumber

        binding.back.setOnClickListener { findNavController().popBackStack() }
        binding.fullName.text = navArgs.name
        binding.nameFirst.text = navArgs.name[0].toString()
        binding.phoneNumber.text = phoneNumber

        binding.linearCall.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialIntent)
        }

        binding.linearMessage.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
            smsIntent.putExtra("sms_body", "hello bro, what's up?!")
            startActivity(smsIntent)
        }

        requireActivity().window.statusBarColor = Color.parseColor("#b6b7bc")
    }
}