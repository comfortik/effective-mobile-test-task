package com.example.effective_mobile_test_task.di

import com.example.effective_mobile_test_task.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val koinModule = module {
    viewModelOf(::MainViewModel)
}