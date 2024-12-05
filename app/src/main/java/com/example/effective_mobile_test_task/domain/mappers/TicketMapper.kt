package com.example.effective_mobile_test_task.domain.mappers

import com.example.effective_mobile_test_task.data.dto.TicketDto
import com.example.effective_mobile_test_task.domain.models.Ticket
import java.text.SimpleDateFormat

fun TicketDto.toDomain(): Ticket {
    val departureTime =departure.date.substringAfter("T")
    val arrivalTime = arrival.date.substringAfter("T")
    val departureMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(departure.date)?.time ?: 0
    val arrivalMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(arrival.date)?.time ?: 0

    val durationMillis = arrivalMillis - departureMillis

    val durationHours = durationMillis.toDouble() / (1000 * 60 * 60)
    return Ticket(
        badge  = badge,
        price = price.value,
        timeRange = "${departureTime}- ${arrivalTime}",
        departureAirport = departure.airport,
        arrivalAirport = arrival.airport,
        time = String.format("%.1f", durationHours),
        hasTransfer =if(has_transfer==null)null else if(has_transfer)"С пересадкой" else "Без пересадок"
    )
}