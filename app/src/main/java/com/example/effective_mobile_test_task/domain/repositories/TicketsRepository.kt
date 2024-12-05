package com.example.effective_mobile_test_task.domain.repositories

import com.example.effective_mobile_test_task.data.dto.TicketDto

interface TicketsRepository {
    suspend fun getTickets():List<TicketDto>
}