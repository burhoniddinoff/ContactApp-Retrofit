package com.example.contactappwithinternet.presentation.screen.verify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappwithinternet.presentation.data.remote.MyClient
import com.example.contactappwithinternet.presentation.data.remote.api.MyApi
import com.example.contactappwithinternet.presentation.data.remote.request.VerifySmsRequest
import com.example.contactappwithinternet.presentation.data.remote.data.ErrorResponse
import com.example.contactappwithinternet.presentation.data.remote.response.RegisterResponce
import com.example.contactappwithinternet.presentation.data.remote.response.VerifySmsResponce
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifySmsViewModel : ViewModel() {

    private val api = MyClient.retrofit.create(MyApi::class.java)

    private val _user = MutableLiveData<Boolean>()
    val user: LiveData<Boolean> get() = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun verifySms(data: VerifySmsRequest) {
        api.verifySmsCode(data).enqueue(object : Callback<VerifySmsResponce> {
            override fun onResponse(
                call: Call<VerifySmsResponce>,
                response: Response<VerifySmsResponce>,
            ) {
                if (response.isSuccessful) {
                    _user.value = true
                    response.body()?.let {
                        VerifySmsResponce(it.token, it.phone)
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

}