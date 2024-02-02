package com.example.contactappwithinternet.presentation.screen.register

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactappwithinternet.R
import com.example.contactappwithinternet.databinding.ScreenRegisterBinding
import com.example.contactappwithinternet.presentation.data.remote.request.RegisterRequest
import com.example.contactappwithinternet.presentation.data.remote.response.RegisterResponce

class CreateUserScreen : Fragment(R.layout.screen_register) {

    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel by viewModels<CreateUserViewModel>()
    private var sms: String = ""

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.user.observe(this, userObserver)
        viewModel.error.observe(this, errorObserver)
        viewModel.error1.observe(this, error1Observer)

        binding.textSignIN.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.createAccount.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            val password = binding.password.text.toString()

            viewModel.registerUser(
                RegisterRequest(
                    firstName, lastName, phoneNumber, password
                )
            )

        }

        requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
    }


    private val error1Observer = Observer<Boolean> {
        if (it) findNavController().navigate(
            CreateUserScreenDirections.actionCreateUserScreenToVerifySmsScreen()
        ) else Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
    }


    private val userObserver = Observer<RegisterResponce> {
        sms = it.sms
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

}