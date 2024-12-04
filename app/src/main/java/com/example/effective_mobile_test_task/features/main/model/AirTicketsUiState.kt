package com.example.effective_mobile_test_task.features.main.model

import com.example.effective_mobile_test_task.domain.models.Offer
import com.example.effective_mobile_test_task.features.common.base.BaseState

data class AirTicketsUiState (
    val offers: List<Offer> = listOf(),
    val departureCity: String = "",
    val arriveCity: String="",
    val isModalWindowVisible: Boolean = false
): BaseState