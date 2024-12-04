package com.example.effective_mobile_test_task.data.sources.remote.apis

import com.example.effective_mobile_test_task.data.dto.OffersResponse
import retrofit2.http.GET

interface OffersApi {
    @GET("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getOffers(): OffersResponse
}


