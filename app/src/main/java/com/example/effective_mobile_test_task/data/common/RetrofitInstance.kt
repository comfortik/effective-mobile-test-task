package com.example.effective_mobile_test_task.data.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private const val BASE_URL = "https://drive.usercontent.google.com/u/0/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inline fun <reified T> createApi(): T {
        return retrofit.create(T::class.java)
    }
}
