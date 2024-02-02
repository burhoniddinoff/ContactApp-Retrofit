package com.example.contactappwithinternet.presentation.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappwithinternet.presentation.data.remote.MyClient
import com.example.contactappwithinternet.presentation.data.remote.api.MyApi
import com.example.contactappwithinternet.presentation.data.remote.request.RegisterRequest
import com.example.contactappwithinternet.presentation.data.remote.data.ErrorResponse
import com.example.contactappwithinternet.presentation.data.remote.response.RegisterResponce
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NAME_SHADOWING")
class CreateUserViewModel : ViewModel() {

    private val api = MyClient.retrofit.create(MyApi::class.java)

    private val _user = MutableLiveData<RegisterResponce>()
    val user: LiveData<RegisterResponce> get() = _user

    private val _error1 = MutableLiveData<Boolean>()
    val error1: LiveData<Boolean> get() = _error1

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun registerUser(data: RegisterRequest) {
        api.registerUser(data).enqueue(object : Callback<RegisterResponce> {
            override fun onResponse(
                call: Call<RegisterResponce>,
                response: Response<RegisterResponce>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _user.value = it
                        _error1.value = true
                    }
                } else {
                    _error1.value = false
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val data = gson.fromJson(
                            response.errorBody()?.string(), ErrorResponse::class.java
                        )
                        _error.value = data.message
                    } else _error.value = "Unknown error!"
                }
            }

            override fun onFailure(call: Call<RegisterResponce>, t: Throwable) {
                _error1.value = false
                _error.value = t.message
            }

        })
    }

}