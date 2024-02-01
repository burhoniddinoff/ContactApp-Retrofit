package com.example.contactappwithinternet.presentation.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyClient {
    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://8a11-94-158-61-221.ngrok-free.app")
        .baseUrl("https://5f14-82-215-92-25.ngrok-free.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}