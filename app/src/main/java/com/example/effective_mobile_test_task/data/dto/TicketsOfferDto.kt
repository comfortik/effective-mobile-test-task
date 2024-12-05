package com.example.effective_mobile_test_task.data.dto

data class TicketsOfferDto(
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: PriceDto
)
