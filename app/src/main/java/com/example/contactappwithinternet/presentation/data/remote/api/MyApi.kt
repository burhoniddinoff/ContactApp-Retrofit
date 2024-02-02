package com.example.contactappwithinternet.presentation.data.remote.api

import com.example.contactappwithinternet.presentation.data.remote.request.CreateContactRequest
import com.example.contactappwithinternet.presentation.data.remote.request.RegisterRequest
import com.example.contactappwithinternet.presentation.data.remote.request.EditContactRequest
import com.example.contactappwithinternet.presentation.data.remote.request.LoginRequest
import com.example.contactappwithinternet.presentation.data.remote.request.VerifySmsRequest
import com.example.contactappwithinternet.presentation.data.remote.response.ContactResponse
import com.example.contactappwithinternet.presentation.data.remote.response.RegisterResponce
import com.example.contactappwithinternet.presentation.data.remote.response.VerifySmsResponce
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MyApi {

    @POST("/api/v1/register")
    fun registerUser(@Body data: RegisterRequest): Call<RegisterResponce>

    @POST("/api/v1/register/verify")
    fun verifySmsCode(@Body data: VerifySmsRequest): Call<VerifySmsResponce>

    @POST("/api/v1/login")
    fun loginUser(@Body data: LoginRequest): Call<VerifySmsResponce>

    @GET("api/v1/contact")
    fun getAllContacts(
        @Header("token") token: String
    ): Call<List<ContactResponse>>

    @POST("api/v1/contact")
    fun addContact(
        @Header("token") token: String,
        @Body data: CreateContactRequest,
    ): Call<ContactResponse>

    @PUT("api/v1/contact")
    fun updateContact(
        @Header("token") token: String,
        @Body data: EditContactRequest,
    ): Call<ContactResponse>

    @DELETE("api/v1/contact")
    fun deleteContact(
        @Header("token") token: String,
        @Query("id") id: Int,
    ): Call<Unit>

}