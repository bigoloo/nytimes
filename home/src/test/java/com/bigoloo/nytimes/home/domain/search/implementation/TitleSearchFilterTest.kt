package com.bigoloo.nytimes.home.domain.search.implementation

import com.bigoloo.nytimes.home.models.Multimedia
import com.bigoloo.nytimes.home.models.Story
import org.junit.Test
import kotlin.test.assertEquals

class TitleSearchFilterTest {

   private  val titleSearchFilter = TitleSearchFilter()


    @Test
    fun `when search term is null filter should returns story  `() {
        val mockStory = Story(
            title = "title", abstract = "", url = "", uri = "", byLine = "Amin",
            Multimedia(""), ""
        )

        val story = titleSearchFilter.filter(null, mockStory)

        assertEquals(mockStory, story)
    }

    @Test
    fun `when search term is empty filter should returns story`() {
        val mockStory = Story(
            title = "title", abstract = "", url = "", uri = "", byLine = "Amin",
            Multimedia(""), ""
        )
        val story = titleSearchFilter.filter("", mockStory)
        assertEquals(mockStory, story)
    }
    @Test
    fun `when title contains search term filter should returns story`(){
        val mockStory = Story(
            title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
            Multimedia(""), ""
        )
        val story = titleSearchFilter.filter("real", mockStory)
        assertEquals(mockStory, story)
    }

    @Test
    fun `when title not contains search term filter should returns null`(){
        val mockStory = Story(
            title = "this is real title ", abstract = "", url = "", uri = "", byLine = "Amin",
            Multimedia(""), ""
        )
        val story = titleSearchFilter.filter("fake", mockStory)
        assertEquals(null, story)
    }
}