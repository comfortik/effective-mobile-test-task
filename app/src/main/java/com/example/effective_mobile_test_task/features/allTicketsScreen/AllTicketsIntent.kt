package com.example.effective_mobile_test_task.features.allTicketsScreen

import com.example.effective_mobile_test_task.features.common.base.BaseIntent
import java.time.LocalDate

sealed interface AllTicketsIntent: BaseIntent {
    data class OnDepartureCityChanged(val city: String): AllTicketsIntent
    data class OnArrivedCityChanged(val city: String): AllTicketsIntent
    data class OnDateChanged(val date: LocalDate) : AllTicketsIntent
    data class OpenDatePicker(val type: DatePickerType): AllTicketsIntent
    data object CloseDatePicker: AllTicketsIntent

}

sealed interface DatePickerType {
    data object BackDatePicker: DatePickerType
    data object ToDatePicker: DatePickerType
}
