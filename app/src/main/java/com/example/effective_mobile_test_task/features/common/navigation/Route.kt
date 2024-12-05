package com.example.effective_mobile_test_task.features.common.navigation

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
sealed interface Route{
    @Serializable
    data object AirTicketsRoute: Route
    @Serializable
    data object SearchRoute: Route
    @Serializable
    data class AllTicketsRoute(
        val departureCity: String,
        val arrivedCity: String
    ): Route
    @Serializable
    data object PlugRoute: Route
    @Serializable
    data class TicketsRoute(
        val departureCity: String,
         val arrivedCity: String,
        val date: String
    ): Route
}