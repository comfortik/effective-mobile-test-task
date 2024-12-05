package com.example.effective_mobile_test_task.features.allTicketsScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.effective_mobile_test_task.domain.mappers.toDomain
import com.example.effective_mobile_test_task.domain.repositories.TicketsOffersRepository
import com.example.effective_mobile_test_task.features.common.base.BaseAction
import com.example.effective_mobile_test_task.features.common.base.BaseViewModel
import kotlinx.coroutines.launch

class AllTicketsViewModel(
    private val ticketsOffersRepository: TicketsOffersRepository
): BaseViewModel<AllTicketsUiState,BaseAction, AllTicketsIntent>() {
    override fun createInitState(): AllTicketsUiState  = AllTicketsUiState()

    init {
        viewModelScope.launch {
            try {
                val offers = ticketsOffersRepository.getTicketOffers().map {
                    it.toDomain()
                }
                _state.value = state.copy(
                    ticketOffers = offers,
                    isLoading = false
                )
            }catch (e: Exception){
                Log.e("All tickets view model", e.message.toString())
            }
        }

    }

    override fun handleIntent(intent: AllTicketsIntent) {
        when(intent){
            is AllTicketsIntent.OnArrivedCityChanged -> {
                _state.value = state.copy(
                    arrivedCity = intent.city
                )
            }
            is AllTicketsIntent.OnDepartureCityChanged -> {
                _state.value = state.copy(
                    departureCity = intent.city
                )
            }

            is AllTicketsIntent.OnDateChanged -> {
                _state.value = state.copy(
                    backDate = if(state.datePickerType is DatePickerType.BackDatePicker) intent.date else state.backDate,
                    toDate = if(state.datePickerType is DatePickerType.ToDatePicker) intent.date else state.toDate,
                    isDatePickerVisible = false
                )
            }
            is AllTicketsIntent.OpenDatePicker -> {
                _state.value = state.copy(
                    isDatePickerVisible = true,
                    datePickerType = intent.type
                )
            }

            AllTicketsIntent.CloseDatePicker -> {
                _state.value = state.copy(
                    isDatePickerVisible = false
                )
            }
        }
    }

}