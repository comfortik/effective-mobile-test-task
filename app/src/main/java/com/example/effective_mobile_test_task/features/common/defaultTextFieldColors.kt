package com.example.effective_mobile_test_task.features.common

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.effective_mobile_test_task.R

@Composable
fun defaultTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        disabledContainerColor = colorResource(R.color.item_light_gray),
        focusedContainerColor = colorResource(R.color.item_light_gray),
        unfocusedContainerColor = colorResource(R.color.item_light_gray),
        focusedTextColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedTextColor = Color.White
    )
}
