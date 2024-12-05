package com.example.effective_mobile_test_task.data.repositoriesImpl

import com.example.effective_mobile_test_task.data.dto.TicketDto
import com.example.effective_mobile_test_task.data.sources.remote.apis.TicketsApi
import com.example.effective_mobile_test_task.domain.repositories.TicketsRepository

class TicketRepositoryImpl(
    val remote: TicketsApi
): TicketsRepository {
    override suspend fun getTickets(): List<TicketDto>  =
        remote.getTickets().tickets
}