package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.exception.EmptyListException
import com.raflisalam.fakeneflix.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    private val _search = MutableLiveData<List<Any>>()
    val results: LiveData<List<Any>> = _search

    fun fetchSearchResult(query: String) {
        viewModelScope.launch {
            try {
                val result = repository.fetchSearchResults(query)
                _search.value = result
            } catch (e: Exception) {
                throw EmptyListException(e.message.toString())
            }
        }
    }
}