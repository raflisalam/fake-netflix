package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.enums.MediaType
import com.raflisalam.fakeneflix.common.exception.ApiException
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult
import com.raflisalam.fakeneflix.domain.usecase.discover.GetDiscoverUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val useCase: GetDiscoverUseCase
): ViewModel() {

    private val _discoverItem = MutableLiveData<List<DiscoverResult>>()
    val getDiscover: LiveData<List<DiscoverResult>> get() = _discoverItem

    fun fetchDiscoverItemsByGenre(genreId: ArrayList<String>, mediaType: MediaType) {
        viewModelScope.launch {
            try {
                val result = useCase.getSearchByGenre(genreId, mediaType)
                _discoverItem.value = result
            } catch (e: Exception) {
                throw ApiException("Failed to fetch discover list")
            }
        }
    }


}