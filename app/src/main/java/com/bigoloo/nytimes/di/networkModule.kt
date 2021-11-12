package com.bigoloo.nytimes.di

import com.bigoloo.nytimes.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder().client(
            OkHttpClient().newBuilder().addInterceptor(AuthInterceptor())
                .build()
        )
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .build()
    }
}