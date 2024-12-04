package com.example.effective_mobile_test_task

import com.example.effective_mobile_test_task.features.common.base.BaseViewModel
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem
import com.example.effective_mobile_test_task.features.common.navigation.Route
import com.example.effective_mobile_test_task.features.models.MainAction
import com.example.effective_mobile_test_task.features.models.MainIntent
import com.example.effective_mobile_test_task.features.models.MainState


class MainViewModel: BaseViewModel<MainState, MainAction, MainIntent>() {
    override fun handleIntent(intent: MainIntent) {
        when(intent){
            is MainIntent.OnBottomItemClick -> {
                _state.value = screenState.value.copy(
                    currentNavBarItem = intent.item,
                    currentRoute = intent.item.route
                )
            }
            is MainIntent.OnRouteChanged -> {
                _state.value = screenState.value.copy(
                    currentRoute = intent.route
                )
            }
        }
    }
    override fun createInitState(): MainState = MainState(
        currentNavBarItem = BottomBarItem.AirTicketsItem,
        currentRoute = Route.AirTicketsRoute
    )
}