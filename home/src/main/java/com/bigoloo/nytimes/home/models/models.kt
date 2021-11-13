package com.bigoloo.nytimes.home.models

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TopStoryResponse(@SerialName("results") val results: List<Story>)

@Keep
@Serializable
data class Story(
    @SerialName("title")
    val title: String,
    @SerialName("abstract")
    val abstract: String,
    @SerialName("url")
    val url: String,
    @SerialName("uri")
    val uri: String,
    @SerialName("byline")
    val byLine: String,
    @SerialName("multimedia")
    val multimedia: List<Multimedia>?,
    @SerialName("short_url")
    val shortUrl: String,
)

@Keep
@Serializable
data class Multimedia(
    @SerialName("url")
    val url: String
    )


sealed class Loadable<out T>
object NotLoaded : Loadable<Nothing>()
object Loading : Loadable<Nothing>()
data class Loaded<T>(val data: T) : Loadable<T>()
data class Failed(val throwable: Throwable) : Loadable<Nothing>()