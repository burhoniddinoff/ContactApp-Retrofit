package com.example.contactappwithinternet.presentation.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.contactappwithinternet.presentation.data.remote.MySharedPreference

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
//        instance = this
    }

//    companion object {
//        @SuppressLint("StaticFieldLeak")
//        lateinit var instance: Context
//            private set
//    }


}