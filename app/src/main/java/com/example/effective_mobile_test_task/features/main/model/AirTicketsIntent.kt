package com.example.effective_mobile_test_task.features.main.model

import com.example.effective_mobile_test_task.features.common.base.BaseIntent

sealed interface AirTicketsIntent: BaseIntent {
    data class OnDepartureCityChanged(val city: String): AirTicketsIntent
    data object OnSearchItemClicked: AirTicketsIntent
}