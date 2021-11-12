package com.bigoloo.nytimes.home.datastore

import com.bigoloo.nytimes.home.models.Story

interface StoryCache {
    fun setStoryList(storyList: List<Story>)
    fun getStoryList():List<Story>
    fun invalidate()
}