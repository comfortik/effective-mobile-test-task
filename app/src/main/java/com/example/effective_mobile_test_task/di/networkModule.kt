package com.example.effective_mobile_test_task.di

import com.example.effective_mobile_test_task.data.sources.remote.apis.OffersApi
import com.example.effective_mobile_test_task.data.sources.remote.apis.TicketsApi
import com.example.effective_mobile_test_task.data.sources.remote.apis.TicketsOffersApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {


    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(OffersApi::class.java)
    }
    single {
        get<Retrofit>().create(TicketsOffersApi::class.java)
    }
    single {
        get<Retrofit>().create(TicketsApi::class.java)
    }
}