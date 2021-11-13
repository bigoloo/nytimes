package com.bigoloo.nytimes.di

import com.bigoloo.nytimes.network.interceptor.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }

val networkModule = module {

    single<Retrofit> {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder().client(
            OkHttpClient().newBuilder().addInterceptor(AuthInterceptor())
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                })
                .build()
        )
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .build()
    }
}