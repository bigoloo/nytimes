package com.bigoloo.nytimes.home.datastore.implementation

import com.bigoloo.nytimes.home.datastore.StoryCache
import com.bigoloo.nytimes.home.models.Multimedia
import com.bigoloo.nytimes.home.models.Story
import org.junit.Assert.assertEquals

import org.junit.Test


class InMemoryStoryCacheTest {


    @Test
    fun `when story cache is created it's data should be empty`() {
        val storyCache: StoryCache = InMemoryStoryCache()
        assertEquals(listOf<Story>(), storyCache.getStoryList())
    }

    @Test
    fun `when data is set to cache it should store`() {
        val storyCache: StoryCache = InMemoryStoryCache()
        val mockStoryList = listOf(
            Story(
                title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
                listOf(Multimedia("")), ""
            )
        )
        storyCache.setStoryList(mockStoryList)
        assertEquals(mockStoryList, storyCache.getStoryList())
    }

    @Test
    fun `when invalidate is called cache should be empty`() {
        val storyCache: StoryCache = InMemoryStoryCache()
        val mockStoryList = listOf(
            Story(
                title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
                listOf(Multimedia("")), ""
            )
        )
        storyCache.setStoryList(mockStoryList)
        storyCache.invalidate()
        assertEquals(listOf<Story>(), storyCache.getStoryList())
    }

}