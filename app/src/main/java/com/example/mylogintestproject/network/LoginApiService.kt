package com.example.mylogintestproject.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body

private const val BASE_URL = "http://localhost:4000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LoginApiService {
    @POST("users/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse
}

object LoginApi {
    val retrofitService : LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }
}