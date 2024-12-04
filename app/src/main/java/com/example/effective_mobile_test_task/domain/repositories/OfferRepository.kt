package com.example.effective_mobile_test_task.domain.repositories

import com.example.effective_mobile_test_task.data.dto.OfferDto

interface OfferRepository {
    suspend fun getOffers(): List<OfferDto>
}