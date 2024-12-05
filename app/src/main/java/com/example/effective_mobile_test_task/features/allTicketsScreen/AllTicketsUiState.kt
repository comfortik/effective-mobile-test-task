package com.example.effective_mobile_test_task.features.allTicketsScreen

import com.example.effective_mobile_test_task.domain.models.TicketOffer
import com.example.effective_mobile_test_task.features.common.base.BaseState
import java.time.LocalDate

data class AllTicketsUiState(
    val isLoading: Boolean = true,
    val departureCity: String = "",
    val arrivedCity: String="",
    val backDate: LocalDate?=null,
    val toDate: LocalDate= LocalDate.now(),
    val isDatePickerVisible: Boolean = false,
    val ticketOffers: List<TicketOffer> =listOf(),
    val datePickerType: DatePickerType = DatePickerType.ToDatePicker
): BaseState

