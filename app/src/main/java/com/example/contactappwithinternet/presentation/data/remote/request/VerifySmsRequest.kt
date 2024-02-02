package com.example.contactappwithinternet.presentation.data.remote.request

import com.example.contactappwithinternet.presentation.data.remote.MySharedPreference

data class VerifySmsRequest(
    val phone: String,
    val code: String,
)