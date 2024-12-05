package com.example.effective_mobile_test_task.features.common

fun Char.isCyrillic() = this in '\u0400'..'\u04FF' || this in '\u0500'..'\u052F'