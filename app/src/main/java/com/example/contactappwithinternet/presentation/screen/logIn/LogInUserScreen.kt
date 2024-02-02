package com.example.contactappwithinternet.presentation.screen.logIn

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
import com.example.contactappwithinternet.databinding.ScreenLoginBinding
import com.example.contactappwithinternet.presentation.data.remote.request.LoginRequest

class LogInUserScreen : Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel by viewModels<LogInUserViewModel>()


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.textSignIN.setOnClickListener {
            viewModel.onClickSingUp()
        }

        binding.createAccount.setOnClickListener {
            viewModel.loginUser(
                LoginRequest(
                    binding.phoneNumber.text.toString(), binding.password.text.toString()
                )
            )
        }

        viewModel.user.observe(this, onClickLogin)
        viewModel.onClickSingUp.observe(this, onClickSingUp)
        viewModel.error.observe(this, errorValue)


        requireActivity().window.statusBarColor = Color.parseColor("#ffffff")
    }

    private val errorValue = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val onClickLogin = Observer<Boolean> {
        if (it) {
            findNavController().navigate(LogInUserScreenDirections.actionLogInUserScreenToContactScreen())
        } else {
            Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
        }
    }

    private val onClickSingUp = Observer<Unit> {
        findNavController().navigate(LogInUserScreenDirections.actionLogInUserScreenToCreateUserScreen())
    }

}
