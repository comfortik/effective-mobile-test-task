package com.example.effective_mobile_test_task.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val CustomDarkColorScheme = darkColorScheme(
    primary = Color.Blue,
    onPrimary = Color.White,
    secondary = Color.Gray,
    background = Color.Black,
    surface = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun EffectivemobiletesttaskTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CustomDarkColorScheme,
        typography = Typography,
        content = content
    )
}