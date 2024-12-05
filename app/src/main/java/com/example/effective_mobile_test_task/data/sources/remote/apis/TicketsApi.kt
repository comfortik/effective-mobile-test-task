package com.example.effective_mobile_test_task.data.sources.remote.apis

import com.example.effective_mobile_test_task.data.dto.responses.TicketResponce
import retrofit2.http.GET

interface TicketsApi {
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTickets():TicketResponce
}