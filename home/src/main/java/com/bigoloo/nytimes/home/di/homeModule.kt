package com.bigoloo.nytimes.home.di

import com.bigoloo.nytimes.home.network.TopStoryApi
import com.bigoloo.nytimes.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    single<TopStoryApi> {
        get<Retrofit>().create(TopStoryApi::class.java)
    }

    viewModel<HomeViewModel> {
        HomeViewModel(get())
    }
}