package com.example.contactappwithinternet.presentation.data.remote.request

data class CreateContactRequest(
    val firstName: String,
    val lastName: String,
    val phone: String,
)