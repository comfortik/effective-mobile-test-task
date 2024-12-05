package com.example.effective_mobile_test_task.features.ticketsScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.effective_mobile_test_task.domain.mappers.toDomain
import com.example.effective_mobile_test_task.domain.repositories.TicketsRepository
import com.example.effective_mobile_test_task.features.common.base.BaseAction
import com.example.effective_mobile_test_task.features.common.base.BaseViewModel
import com.example.effective_mobile_test_task.features.ticketsScreen.models.TicketsIntent
import com.example.effective_mobile_test_task.features.ticketsScreen.models.TicketsState
import kotlinx.coroutines.launch

class TicketsViewModel(
    val ticketsRepository: TicketsRepository
): BaseViewModel<TicketsState, BaseAction, TicketsIntent>() {
    init {
        viewModelScope.launch {
            try {
                val tickets = ticketsRepository.getTickets()
                Log.d("tickets view model", tickets.joinToString())
                _state.value = state.copy(
                    tickets = tickets.map {
                    it.toDomain()
                },
                    isLoading = false
                )
            }catch (e: Exception){
                Log.e("tickets view model", e.message.toString())
            }
        }
    }

    override fun createInitState(): TicketsState =
        TicketsState()

    override fun handleIntent(intent: TicketsIntent) {

    }
}