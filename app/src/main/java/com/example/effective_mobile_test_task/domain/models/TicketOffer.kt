package com.example.effective_mobile_test_task.domain.models

data class TicketOffer(
    val id: Int,
    val title: String,
    val time_range: String,
    val price: Int
)