package com.bigoloo.nytimes.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api-key", "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj")
            .build()

        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}