package com.example.effective_mobile_test_task.di

import com.example.effective_mobile_test_task.MainViewModel
import com.example.effective_mobile_test_task.features.main.AirTicketsViewModel
import com.example.effective_mobile_test_task.features.searchScreen.SearchViewModel

import com.example.effective_mobile_test_task.data.repositoriesImpl.OfferRepositoryImpl
import com.example.effective_mobile_test_task.domain.repositories.OfferRepository
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val koinModule = module {
    singleOf(::OfferRepositoryImpl) { bind<OfferRepository>() }
    viewModelOf(::MainViewModel)
    viewModelOf(::AirTicketsViewModel)
    viewModelOf(::SearchViewModel)

}
