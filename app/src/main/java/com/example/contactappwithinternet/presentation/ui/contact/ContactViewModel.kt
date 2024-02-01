package com.example.contactappwithinternet.presentation.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactappwithinternet.presentation.data.remote.MyClient
import com.example.contactappwithinternet.presentation.data.remote.api.MyApi
import com.example.contactappwithinternet.presentation.data.remote.request.CreateContactRequest
import com.example.contactappwithinternet.presentation.data.remote.request.EditContactRequest
import com.example.contactappwithinternet.presentation.data.remote.response.ContactResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel : ViewModel() {
    private val api = MyClient.retrofit.create(MyApi::class.java)
    //    private val list = ArrayList<ContactResponse>()

    private val _contact = MutableLiveData<List<ContactResponse>>()
    val contact: LiveData<List<ContactResponse>> get() = _contact

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> get() = _progressBar

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _openAddScreen = MutableLiveData<Unit>()
    val openAddScreen: LiveData<Unit> get() = _openAddScreen


    fun loadAllContact() {
        _progressBar.value = true

        api.getAllContacts().enqueue(object : Callback<List<ContactResponse>> {
            override fun onResponse(
                call: Call<List<ContactResponse>>,
                response: Response<List<ContactResponse>>,
            ) {
                _progressBar.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        _contact.value = it
                    }
                } else {
                    _error.value = response.errorBody().toString()
                }
            }

            override fun onFailure(call: Call<List<ContactResponse>>, t: Throwable) {
                _progressBar.value = false
                _error.value = t.message.toString()
            }
        })
    }


    fun addContact(firstName: String, lastName: String, phone: String) {
        _progressBar.value = true

        val newContactResponse = CreateContactRequest(firstName, lastName, phone)

        api.addContact(newContactResponse).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>,
            ) {
                _progressBar.value = false

                if (response.isSuccessful) loadAllContact()
                else _error.value = response.errorBody().toString()

            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                _progressBar.value = false
                _error.value = t.message.toString()
            }

        })
    }

    fun deleteContact(id: Int) {
        _progressBar.value = true

        api.deleteContact(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                _progressBar.value = false
                if (response.isSuccessful) loadAllContact()
                else _error.value = response.errorBody().toString()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _progressBar.value = false
                _error.value = t.message.toString()
            }
        })
    }

    fun updateContact(data: EditContactRequest) {
        _progressBar.value = true

        api.updateContact(data).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>,
            ) {
                _progressBar.value = false
                if (response.isSuccessful) loadAllContact()
                else _error.value = response.errorBody().toString()
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                _progressBar.value = false
                _error.value = t.message.toString()
            }

        })

    }

    fun openAddScreen() {
        _openAddScreen.value = Unit
    }

}