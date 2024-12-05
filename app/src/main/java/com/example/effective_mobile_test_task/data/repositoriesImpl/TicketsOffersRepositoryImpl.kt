package com.example.effective_mobile_test_task.data.repositoriesImpl

import com.example.effective_mobile_test_task.data.dto.PriceDto
import com.example.effective_mobile_test_task.data.dto.TicketsOfferDto
import com.example.effective_mobile_test_task.data.sources.remote.apis.TicketsOffersApi
import com.example.effective_mobile_test_task.domain.repositories.TicketsOffersRepository

class TicketsOffersRepositoryImpl(
    val remote: TicketsOffersApi
): TicketsOffersRepository {
    override suspend fun getTicketOffers(): List<TicketsOfferDto> =
        remote.getTicketsOffers().tickets_offers?: listOf()

}