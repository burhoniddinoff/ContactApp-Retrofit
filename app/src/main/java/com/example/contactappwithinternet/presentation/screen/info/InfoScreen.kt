package com.example.contactappwithinternet.presentation.screen.info

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
import com.example.contactappwithinternet.presentation.data.remote.data.CallHistoryResponse
import com.example.contactappwithinternet.presentation.screen.adapter.CallHistoryAdapter
import java.util.Calendar

class InfoScreen : Fragment(R.layout.screen_info) {

    private val binding by viewBinding(ScreenInfoBinding::bind)
    private val navArgs: InfoScreenArgs by navArgs()
    private val adapter by lazy { CallHistoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val phoneNumber = navArgs.phoneNumber

        binding.recycler.adapter = adapter

        binding.back.setOnClickListener { findNavController().popBackStack() }
        binding.fullName.text = navArgs.name
        binding.nameFirst.text = navArgs.name[0].toString()
        binding.phoneNumber.text = phoneNumber

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based
        val year = calendar.get(Calendar.YEAR)

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        val data = CallHistoryResponse(
            0, "$day.$month.$year", "$hour:$minute:$second", "Cancelled Call", "12"
        )

        binding.linearCall.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            adapter.submitList(listOf(data))
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