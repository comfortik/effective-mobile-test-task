package com.example.effective_mobile_test_task.features.main.models

import com.example.effective_mobile_test_task.features.common.base.BaseIntent
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem
import com.example.effective_mobile_test_task.features.common.navigation.Route

sealed interface MainIntent: BaseIntent {
    data class OnBottomItemClick(val item: BottomBarItem): MainIntent
    data class OnRouteChanged(val route: Route): MainIntent
}