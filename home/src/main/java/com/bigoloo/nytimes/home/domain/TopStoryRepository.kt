package com.bigoloo.nytimes.home.domain

import com.bigoloo.nytimes.home.models.Story

interface TopStoryRepository {
    suspend fun fetchTopNews(searchTerm: String): List<Story>
}