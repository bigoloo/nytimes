package com.bigoloo.nytimes.home.domain.search

import com.bigoloo.nytimes.home.models.Story

interface SearchFilter {
    fun filter(searchTerm: String?, story: Story): Story?
}