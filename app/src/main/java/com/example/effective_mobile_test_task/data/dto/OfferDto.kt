package com.example.effective_mobile_test_task.data.dto

data class OfferDto(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDto
)