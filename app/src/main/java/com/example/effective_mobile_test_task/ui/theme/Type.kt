package com.example.effective_mobile_test_task.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.effective_mobile_test_task.features.common.SFProDisplay

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    displayMedium = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
