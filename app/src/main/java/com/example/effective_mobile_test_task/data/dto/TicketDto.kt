package com.example.effective_mobile_test_task.data.dto

data class TicketDto (
    val id: Int,
    val badge: String?,
    val price: PriceDto,
    val provider_name: String,
    val company: String,
    val departure: CityDto,
    val arrival: CityDto,
    val has_transfer: Boolean?,
    val has_visa_transfer: Boolean,
    val luggage: LuggageDto,
    val hand_luggage: LuggageDto,
    val is_returnable: Boolean,
    val is_exchangable: Boolean

)
data class LuggageDto(
    val has_luggage: Boolean,
    val price: PriceDto
)
data class CityDto(
    val town: String,
    val date: String,
    val airport: String
)