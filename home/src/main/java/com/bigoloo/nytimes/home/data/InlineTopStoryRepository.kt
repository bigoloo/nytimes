package com.bigoloo.nytimes.home.data

import com.bigoloo.nytimes.home.domain.TopStoryRepository
import com.bigoloo.nytimes.home.models.Story
import com.bigoloo.nytimes.home.network.TopStoryApi

class InlineTopStoryRepository(private val topStoryApi: TopStoryApi) : TopStoryRepository {
    override suspend fun fetchTopNews(searchTerm: String): List<Story> {
        return topStoryApi.getTopStory().results
    }
}