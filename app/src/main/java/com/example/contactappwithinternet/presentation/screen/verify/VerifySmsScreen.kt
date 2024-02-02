package com.example.contactappwithinternet.presentation.screen.verify

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactappwithinternet.R
import com.example.contactappwithinternet.databinding.ScreenVerifySmsCodeBinding
import com.example.contactappwithinternet.presentation.data.remote.request.VerifySmsRequest
import com.example.contactappwithinternet.presentation.data.remote.response.VerifySmsResponce

class VerifySmsScreen : Fragment(R.layout.screen_verify_sms_code) {

    private val binding by viewBinding(ScreenVerifySmsCodeBinding::bind)
    private val viewModel by viewModels<VerifySmsViewModel>()
    private val screenArgs: VerifySmsScreenArgs by navArgs()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val phone = screenArgs.phone

        viewModel.user.observe(this, userObserver)
        viewModel.error.observe(this, errorObserver)

        binding.phoneNumber.text = phone

        binding.createAccount.setOnClickListener {
            viewModel.verifySms(
                VerifySmsRequest(
                    binding.phoneNumber.text.toString(), binding.code.text.toString()
                )
            )
        }

    }

    private val userObserver = Observer<Boolean> {
        if (it) findNavController().navigate(VerifySmsScreenDirections.actionVerifySmsScreenToContactScreen())
        else Toast.makeText(requireContext(), "Error !", Toast.LENGTH_SHORT).show()
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

}