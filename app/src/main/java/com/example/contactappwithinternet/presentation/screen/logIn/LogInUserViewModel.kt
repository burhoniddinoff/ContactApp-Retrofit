package com.example.contactappwithinternet.presentation.screen.logIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappwithinternet.presentation.data.remote.MyClient
import com.example.contactappwithinternet.presentation.data.remote.api.MyApi
import com.example.contactappwithinternet.presentation.data.remote.request.LoginRequest
import com.example.contactappwithinternet.presentation.data.remote.data.ErrorResponse
import com.example.contactappwithinternet.presentation.data.remote.response.VerifySmsResponce
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInUserViewModel : ViewModel() {

    private val api = MyClient.retrofit.create(MyApi::class.java)

    private val _user = MutableLiveData<Boolean>()
    val user: LiveData<Boolean> get() = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _onClickSingUp = MutableLiveData<Unit>()
    val onClickSingUp: LiveData<Unit> get() = _onClickSingUp


    fun loginUser(data: LoginRequest) {
        api.loginUser(data).enqueue(object : Callback<VerifySmsResponce> {
            override fun onResponse(
                call: Call<VerifySmsResponce>,
                response: Response<VerifySmsResponce>,
            ) {
                if (response.isSuccessful) {
                    VerifySmsResponce(response.body()!!.token, response.body()!!.phone)
                    response.body()?.let {
                        _user.value = true
                    }
                } else {
                    _user.value = false
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val data = gson.fromJson(
                            response.errorBody()?.string(), ErrorResponse::class.java
                        )
                        _error.value = data.message
                    } else _error.value = "Unknown error!"
                }
            }

            override fun onFailure(call: Call<VerifySmsResponce>, t: Throwable) {
                _user.value = false
                _error.value = t.message
            }
        })
    }

    fun onClickSingUp() {
        _onClickSingUp.value = Unit
    }

}