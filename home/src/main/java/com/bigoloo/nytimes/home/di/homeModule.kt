package com.bigoloo.nytimes.home.di

import com.bigoloo.nytimes.home.data.InlineTopStoryRepository
import com.bigoloo.nytimes.home.datastore.StoryCache
import com.bigoloo.nytimes.home.datastore.implementation.InMemoryStoryCache
import com.bigoloo.nytimes.home.domain.TopStoryRepository
import com.bigoloo.nytimes.home.domain.search.SearchFilter
import com.bigoloo.nytimes.home.domain.search.implementation.TitleSearchFilter
import com.bigoloo.nytimes.home.network.TopStoryApi
import com.bigoloo.nytimes.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    single<TopStoryApi> {
        get<Retrofit>().create(TopStoryApi::class.java)
    }

    single<SearchFilter> {
        TitleSearchFilter()
    }
    single<StoryCache> {
        InMemoryStoryCache()
    }
    single<TopStoryRepository> {
        InlineTopStoryRepository(get(), get(), get())
    }
    viewModel<HomeViewModel> {
        HomeViewModel(get())
    }
}