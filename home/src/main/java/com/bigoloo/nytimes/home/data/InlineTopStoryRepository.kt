package com.bigoloo.nytimes.home.data

import com.bigoloo.nytimes.home.datastore.StoryCache
import com.bigoloo.nytimes.home.domain.TopStoryRepository
import com.bigoloo.nytimes.home.domain.search.SearchFilter
import com.bigoloo.nytimes.home.models.Story
import com.bigoloo.nytimes.home.network.TopStoryApi


class InlineTopStoryRepository(
    private val topStoryApi: TopStoryApi,
    private val storyCache: StoryCache,
    private val searchFilter: SearchFilter

) : TopStoryRepository {
    override suspend fun fetchTopNews(searchTerm: String): List<Story> {
        return fetchTopStoryList().mapNotNull { searchFilter.filter(searchTerm, it) }
    }

    private suspend fun fetchTopStoryList(): List<Story> {
        if (storyCache.getStoryList().isEmpty()) {
            val result = topStoryApi.getTopStory().results
            storyCache.setStoryList(result)
        }
        return storyCache.getStoryList()
    }
}