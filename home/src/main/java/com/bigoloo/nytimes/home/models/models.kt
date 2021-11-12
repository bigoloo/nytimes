package com.bigoloo.nytimes.home.models

import androidx.annotation.Keep

@Keep
data class TopStoryResponse(val results: List<Story>)

data class Story(
    val title: String, val abstract: String,
    val url: String, val uri: String, val byLine: String,
    val multimedia: Multimedia,
    val shortUrl: String,
)

data class Multimedia(val url: String)