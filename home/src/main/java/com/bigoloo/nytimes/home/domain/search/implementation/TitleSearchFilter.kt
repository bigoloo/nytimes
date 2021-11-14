package com.bigoloo.nytimes.home.domain.search.implementation

import com.bigoloo.nytimes.home.domain.search.SearchFilter
import com.bigoloo.nytimes.home.models.Story

class TitleSearchFilter : SearchFilter {
    override fun filter(searchTerm: String?, story: Story): Story? {
        return when {
            searchTerm.isNullOrEmpty() -> story
            else -> {
                if (story.title.lowercase().contains(searchTerm.lowercase())) {
                    story
                } else null
            }
        }
    }
}