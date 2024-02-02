package com.example.contactappwithinternet.presentation.data.remote.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val password: String,
)