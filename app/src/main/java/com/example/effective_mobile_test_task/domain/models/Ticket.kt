package com.example.effective_mobile_test_task.domain.models

data class Ticket (
    val badge: String?,
    val price: Int,
    val timeRange: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val time: String,
    val hasTransfer: String?
)
