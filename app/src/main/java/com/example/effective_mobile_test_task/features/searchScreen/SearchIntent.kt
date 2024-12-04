package com.example.effective_mobile_test_task.features.searchScreen

import com.example.effective_mobile_test_task.features.common.base.BaseIntent

sealed interface SearchIntent: BaseIntent{
    data class onDepartureCityChanged(val city: String): SearchIntent
    data class OnArrivedCityChanged(val city: String): SearchIntent
}
