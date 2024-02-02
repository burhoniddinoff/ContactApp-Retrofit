package com.example.contactappwithinternet.presentation.data.remote

import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("Contact", Context.MODE_PRIVATE)
    }

    fun setToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String = sharedPreferences.getString("token", "")!!

}