package com.example.effective_mobile_test_task.features.searchScreen

import com.example.effective_mobile_test_task.features.common.base.BaseViewModel

class SearchViewModel : BaseViewModel<SearchState, SearchAction, SearchIntent>(){
    override fun createInitState(): SearchState  =
        SearchState()

    override fun handleIntent(intent: SearchIntent) {
        when(intent){
            is SearchIntent.OnArrivedCityChanged -> {
                _state.value = state.copy(
                    arriveCity = intent.city
                )
            }
            is SearchIntent.onDepartureCityChanged -> {
                _state.value = state.copy(
                    departureCity = intent.city
                )
            }
        }
    }
}