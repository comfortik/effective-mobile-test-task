package com.example.effective_mobile_test_task.features.common.navigation

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route{
    @Serializable
    data object MainScreenRoute: Route
    @Serializable
    data object SearchRoute: Route
    @Serializable
    data object AllTicketsRoute: Route
    @Serializable
    data object PlugRoute: Route
}