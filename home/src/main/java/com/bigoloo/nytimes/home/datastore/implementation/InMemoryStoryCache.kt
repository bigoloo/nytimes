package com.bigoloo.nytimes.home.datastore.implementation

import com.bigoloo.nytimes.home.datastore.StoryCache
import com.bigoloo.nytimes.home.models.Story

class InMemoryStoryCache : StoryCache {
    private val storyCache = mutableListOf<Story>()
    override fun setStoryList(storyList: List<Story>) {
        storyCache.clear()
        storyCache.addAll(storyList)
    }

    override fun getStoryList(): List<Story> {
        return storyCache
    }

    override fun invalidate() {
        storyCache.clear()
    }
}