package com.example.contactappwithinternet.presentation.data.remote.api

import com.example.contactappwithinternet.presentation.data.remote.request.CreateContactRequest
import com.example.contactappwithinternet.presentation.data.remote.request.EditContactRequest
import com.example.contactappwithinternet.presentation.data.remote.response.ContactResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MyApi {

    @GET("api/v1/contact")
    fun getAllContacts(): Call<List<ContactResponse>>

    @POST("api/v1/contact")
    fun addContact(@Body data: CreateContactRequest): Call<ContactResponse>

    @PUT("api/v1/contact")
    fun updateContact(@Body data: EditContactRequest): Call<ContactResponse>

    @DELETE("api/v1/contact")
    fun deleteContact(@Query("id") id: Int): Call<Unit>


}