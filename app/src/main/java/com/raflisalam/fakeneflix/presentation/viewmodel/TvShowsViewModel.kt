package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_popular.GetPopularTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val popular_TvShows: GetPopularTvShowsUseCase
) : ViewModel() {

    private val _getPopularTvShows = MutableLiveData<Status<List<TvShows>>>()
    val getPopularTvShows: LiveData<Status<List<TvShows>>> = _getPopularTvShows

    fun fetchPopularTvShows(page: Int) {
        viewModelScope.launch {
            _getPopularTvShows.value = Status.Loading()
            try {
                popular_TvShows.invoke(page).collect {
                    _getPopularTvShows.value = it
                }
            } catch (e: Exception) {
                _getPopularTvShows.value = Status.Error("Failed to fetch popular tv shows list")
            }
        }
    }
}