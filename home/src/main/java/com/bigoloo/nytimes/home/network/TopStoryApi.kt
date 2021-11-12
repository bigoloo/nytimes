package com.bigoloo.nytimes.home.network

import com.bigoloo.nytimes.home.models.TopStoryResponse
import retrofit2.http.GET

interface TopStoryApi {
    @GET("/world.json")
    fun getTopStory(): TopStoryResponse
}