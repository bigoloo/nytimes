package com.bigoloo.nytimes.home.data

import com.bigoloo.nytimes.home.datastore.StoryCache
import com.bigoloo.nytimes.home.datastore.implementation.InMemoryStoryCache
import com.bigoloo.nytimes.home.domain.search.SearchFilter
import com.bigoloo.nytimes.home.models.Multimedia
import com.bigoloo.nytimes.home.models.Story
import com.bigoloo.nytimes.home.models.TopStoryResponse
import com.bigoloo.nytimes.home.network.TopStoryApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class InlineTopStoryRepositoryTest {


    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @RelaxedMockK
    lateinit var topStoryApi: TopStoryApi

    val transparentSearchFilter = object : SearchFilter {
        override fun filter(searchTerm: String?, story: Story): Story? {
            return story
        }

    }


    @Test
    fun `when story  cache is  empty api should be called`() = runBlocking {

        val apiResult = listOf(
            Story(
                title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
                listOf(Multimedia("")), ""
            )
        )
        coEvery { topStoryApi.getTopStory() } returns TopStoryResponse(apiResult)
        val storyCache: StoryCache = InMemoryStoryCache()


        val topStoryRepository =
            InlineTopStoryRepository(topStoryApi, storyCache, transparentSearchFilter)

        val result = topStoryRepository.fetchTopNews(null)
        coVerify(exactly = 1) {
            topStoryApi.getTopStory()
        }

    }

    @Test
    fun `when story cache is  empty, cache should fill with api result`() = runBlocking {
        val apiResult = listOf(
            Story(
                title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
                listOf(Multimedia("")), ""
            )
        )
        coEvery { topStoryApi.getTopStory() } returns TopStoryResponse(apiResult)
        val storyCache: StoryCache = InMemoryStoryCache()
        val topStoryRepository =
            InlineTopStoryRepository(topStoryApi, storyCache, transparentSearchFilter)

        val result = topStoryRepository.fetchTopNews(null)
        assertEquals(apiResult, result)
    }

    @Test
    fun `when story cache has Items, api shouldn't be called`() = runBlocking {
        val cacheItems = listOf(
            Story(
                title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
                listOf(Multimedia("")), ""
            )
        )
        val apiResponse = listOf(
            Story(
                title = "this is real title 2 ", abstract = "", url = "", uri = "", byLine = "Amin",
                listOf(Multimedia("")), ""
            )
        )
        coEvery { topStoryApi.getTopStory() } returns TopStoryResponse(apiResponse)
        val storyCache: StoryCache = InMemoryStoryCache()
        storyCache.setStoryList(cacheItems)
        val topStoryRepository =
            InlineTopStoryRepository(topStoryApi, storyCache, transparentSearchFilter)

        val result = topStoryRepository.fetchTopNews(null)
        coVerify(exactly = 0) {
            topStoryApi.getTopStory()
        }
        assertEquals(cacheItems, result)
    }

}