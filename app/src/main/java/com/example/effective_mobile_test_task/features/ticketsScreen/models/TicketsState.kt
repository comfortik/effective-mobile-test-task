package com.example.effective_mobile_test_task.features.ticketsScreen.models

import com.example.effective_mobile_test_task.domain.models.Ticket
import com.example.effective_mobile_test_task.features.common.base.BaseState

data class TicketsState (
    val isLoading: Boolean = true,
    val tickets: List<Ticket> = listOf()
): BaseState
