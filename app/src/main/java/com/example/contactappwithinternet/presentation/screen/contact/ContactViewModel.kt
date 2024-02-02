package com.example.contactappwithinternet.presentation.screen.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappwithinternet.presentation.data.remote.MyClient
import com.example.contactappwithinternet.presentation.data.remote.MySharedPreference
import com.example.contactappwithinternet.presentation.data.remote.api.MyApi
import com.example.contactappwithinternet.presentation.data.remote.request.CreateContactRequest
import com.example.contactappwithinternet.presentation.data.remote.request.EditContactRequest
import com.example.contactappwithinternet.presentation.data.remote.response.ContactResponse
import com.example.contactappwithinternet.presentation.data.remote.data.ErrorResponse
import com.example.contactappwithinternet.presentation.utils.myLog
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel() : ViewModel() {
    private val api = MyClient.retrofit.create(MyApi::class.java)

    private val _contact = MutableLiveData<List<ContactResponse>>()
    val contact: LiveData<List<ContactResponse>> get() = _contact

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> get() = _progressBar

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _success = MutableLiveData<String>()
    val success: LiveData<String> get() = _success

    private val token = MySharedPreference.getToken()

    fun loadAllContact() {
        _progressBar.value = true

        api.getAllContacts(MySharedPreference.getToken()).enqueue(object : Callback<List<ContactResponse>> {

            override fun onResponse(

                call: Call<List<ContactResponse>>,
                response: Response<List<ContactResponse>>,
            ) {
                MySharedPreference.getToken().myLog()
                _progressBar.value = false
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        _contact.value = it
                    }
                } else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val data = gson.fromJson(
                            response.errorBody()!!.string(), ErrorResponse::class.java // ->
                        )
                        _error.value = data.message
                    } else _error.value = "Unknown error!"
                }
            }

            override fun onFailure(call: Call<List<ContactResponse>>, t: Throwable) {
                _progressBar.value = false
                _error.value = "Error: ${t.message}"
            }
        })
    }

    fun addContact(firstName: String, lastName: String, phone: String) {
        _progressBar.value = true

        val newContactResponse = CreateContactRequest(firstName, lastName, phone)

        api.addContact(token, newContactResponse).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>,
            ) {
                _progressBar.value = false

                if (response.isSuccessful && response.body() != null) {
                    loadAllContact()
                    _success.value = "Contact successfully added!"
                } else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val data = gson.fromJson(
                            response.errorBody()?.string(), ErrorResponse::class.java
                        )
                        _error.value = data.message
                    } else _error.value = "Unknown error!"
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                _progressBar.value = false
                _error.value = "Error: ${t.message}"
            }

        })
    }

    fun deleteContact(id: Int) {
        _progressBar.value = true

        api.deleteContact(token, id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                _progressBar.value = false
                if (response.isSuccessful && response.body() != null) {
                    loadAllContact()
                    _success.value = "Contact deleted successfully!"
                } else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val data = gson.fromJson(
                            response.errorBody()?.string(), ErrorResponse::class.java
                        )
                        _error.value = data.message
                    } else _error.value = "Unknown error!"
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _progressBar.value = false
                _error.value = "Error: ${t.message}"
            }
        })
    }

    fun updateContact(data: EditContactRequest) {
        _progressBar.value = true

        api.updateContact(token, data).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>,
            ) {
                _progressBar.value = false
                if (response.isSuccessful && response.body() != null) {
                    loadAllContact()
                    _success.value = "Contact updated successfully!"
                } else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val data: ErrorResponse = gson.fromJson(
                            response.errorBody()?.string(), ErrorResponse::class.java
                        )
                        _error.value = data.message
                    } else _error.value = "Unknown error!"
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                _progressBar.value = false
                _error.value = "Error: ${t.message}"
            }
        })
    }

}