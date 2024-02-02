package com.example.contactappwithinternet.presentation.data.remote

import com.example.contactappwithinternet.presentation.app.MyApp
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyClient {

//    private val client = OkHttpClient.Builder().addInterceptor(
//        ChuckInterceptor(MyApp.instance)
//    ).build()

//        .client(client)


    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://8a11-94-158-61-221.ngrok-free.app")
//        .baseUrl("https://5f14-82-215-92-25.ngrok-free.app/")
//        .baseUrl("https://d8c3-82-215-92-25.ngrok-free.app/")
//        .baseUrl("https://a0af-82-215-92-25.ngrok-free.app/")
//        .baseUrl("https://5613-195-158-16-140.ngrok-free.app/")
//        .baseUrl("https://8312-95-214-211-142.ngrok-free.app/")

        .baseUrl("https://6fbb-82-215-92-25.ngrok-free.app/")
        .addConverterFactory(GsonConverterFactory.create()).build()

}