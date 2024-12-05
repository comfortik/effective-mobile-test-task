package com.example.effective_mobile_test_task.domain.repositories

import com.example.effective_mobile_test_task.data.dto.TicketsOfferDto

interface TicketsOffersRepository {
    suspend fun getTicketOffers(): List<TicketsOfferDto>
}