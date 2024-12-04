package com.example.effective_mobile_test_task.features.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route{
    @Serializable
    data object AirTicketsRoute: Route
    @Serializable
    data object SearchRoute: Route
    @Serializable
    data object AllTicketsRoute: Route
    @Serializable
    data object PlugRoute: Route
}