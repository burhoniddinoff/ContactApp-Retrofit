package com.example.contactappwithinternet.presentation.data.remote.response

import com.example.contactappwithinternet.presentation.data.remote.MySharedPreference

data class VerifySmsResponce(
    val token: String,
    val phone: String,
) {
    init {
        MySharedPreference.setToken(token)
    }
}