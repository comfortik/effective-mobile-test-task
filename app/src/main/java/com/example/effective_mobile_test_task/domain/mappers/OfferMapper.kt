package com.example.effective_mobile_test_task.domain.mappers

import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.data.dto.OfferDto
import com.example.effective_mobile_test_task.domain.models.Offer

fun OfferDto.toDomain(): Offer{
    val image =when(id){
        1-> R.drawable.die_antwoord
        2-> R.drawable.sockrat
        3-> R.drawable.lampabict
        else-> R.drawable.ic_profile
    }
    return Offer(
        id = id,
        title = title,
        town = town,
        image = image,
        price = price.value
    )

}