package com.example.effective_mobile_test_task.data.repositoriesImpl

import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.data.dto.OfferDto
import com.example.effective_mobile_test_task.data.sources.remote.apis.OffersApi
import com.example.effective_mobile_test_task.domain.models.Offer
import com.example.effective_mobile_test_task.domain.repositories.OfferRepository

class OfferRepositoryImpl(
    val remote: OffersApi
): OfferRepository {
    override suspend fun getOffers(): List<OfferDto> {
        return remote.getOffers().offers
    }
}