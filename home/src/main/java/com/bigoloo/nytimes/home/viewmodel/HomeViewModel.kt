package com.bigoloo.nytimes.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigoloo.nytimes.home.domain.TopStoryRepository
import com.bigoloo.nytimes.home.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val topStoryRepository: TopStoryRepository) : ViewModel() {

    private val _state: MutableStateFlow<Loadable<List<Story>>> =
        MutableStateFlow(NotLoaded)

    val state: StateFlow<Loadable<List<Story>>>
        get() = _state

    init {
        doSearch(null)
    }


    fun doSearch(searchTerm: String?) {
        Log.d(
            "doSearch", "/$searchTerm/"
        )
        if (_state.value is Loading) return
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                topStoryRepository.fetchTopNews(searchTerm)
            }.fold({

                  Log.d(
                      "doSearch", "${it.size}"
                  )
                  _state.value = Loaded(it)

            }, {

                it.printStackTrace()
                _state.value = Failed(it)
            })
        }
    }

}