package com.example.effective_mobile_test_task.features.main.models

import com.example.effective_mobile_test_task.features.common.base.BaseState
import com.example.effective_mobile_test_task.features.common.navigation.BottomBarItem
import com.example.effective_mobile_test_task.features.common.navigation.Route

data class MainState (
    val currentNavBarItem: BottomBarItem  ,
    val currentRoute: Route
): BaseState