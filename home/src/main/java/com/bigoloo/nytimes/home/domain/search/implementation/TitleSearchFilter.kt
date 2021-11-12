package com.bigoloo.nytimes.home.domain.search.implementation

import com.bigoloo.nytimes.home.domain.search.SearchFilter
import com.bigoloo.nytimes.home.models.Story

class TitleSearchFilter : SearchFilter {
    override fun filter(searchTerm: String?, story: Story): Story? {
        return when {
            searchTerm.isNullOrEmpty() -> story
            else -> {
                if (story.title.contains(searchTerm)) {
                    story
                } else null
            }
        }
    }
}