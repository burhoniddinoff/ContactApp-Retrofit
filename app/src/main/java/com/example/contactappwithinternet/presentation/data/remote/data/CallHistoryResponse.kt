package com.example.contactappwithinternet.presentation.data.remote.data

data class CallHistoryResponse(
    val id: Int,
    val date: String,
    val time: String,
    val call: String,
    val second: String,
)