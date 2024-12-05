package com.example.effective_mobile_test_task.domain.mappers

import com.example.effective_mobile_test_task.data.dto.TicketsOfferDto
import com.example.effective_mobile_test_task.domain.models.TicketOffer

fun TicketsOfferDto.toDomain()=
    TicketOffer(
        id = id,
        title = title,
        time_range = time_range.joinToString(),
        price = price.value
    )
