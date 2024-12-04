package com.example.effective_mobile_test_task.features.searchScreen

import com.example.effective_mobile_test_task.features.common.base.BaseState

data class SearchState(
    val departureCity: String = "",
    val arriveCity: String  = "",
): BaseState