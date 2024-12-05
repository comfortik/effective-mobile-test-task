package com.example.effective_mobile_test_task.features.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.effective_mobile_test_task.domain.mappers.toDomain
import com.example.effective_mobile_test_task.domain.repositories.OfferRepository
import com.example.effective_mobile_test_task.features.main.model.AirTicketsIntent
import com.example.effective_mobile_test_task.features.main.model.AirTicketsUiState
import com.example.effective_mobile_test_task.features.common.base.BaseAction
import com.example.effective_mobile_test_task.features.common.base.BaseViewModel
import com.example.effective_mobile_test_task.features.main.model.AirTicketAction
import kotlinx.coroutines.launch

class AirTicketsViewModel(
    offersRepository: OfferRepository
):BaseViewModel<AirTicketsUiState, AirTicketAction, AirTicketsIntent>() {
    init {
        viewModelScope.launch {
            try {
                _state.value = state.copy(
                    offers = offersRepository.getOffers().map { it.toDomain() },
                    isLoading = false
                )
            }catch (e: Exception){
                Log.e("air Tickets view model", e.message.toString())
            }
        }

    }
    override fun createInitState(): AirTicketsUiState = AirTicketsUiState()


    override fun handleIntent(intent: AirTicketsIntent) {
        when(intent){
            is AirTicketsIntent.OnDepartureCityChanged -> {
                _state.value = screenState.value.copy(
                    departureCity = intent.city
                )
            }
            is AirTicketsIntent.OnSearchItemClicked -> {
                _action.tryEmit(AirTicketAction.NavigateToSearchScreen)

            }
        }
    }
}



