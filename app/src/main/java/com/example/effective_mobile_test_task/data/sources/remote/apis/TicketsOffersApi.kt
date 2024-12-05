package com.example.effective_mobile_test_task.data.sources.remote.apis

import com.example.effective_mobile_test_task.data.dto.responses.TicketsOffersResponse
import retrofit2.http.GET


interface TicketsOffersApi {
    @GET("uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getTicketsOffers(): TicketsOffersResponse
}