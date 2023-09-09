package com.raflisalam.fakeneflix.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.SeriesIdStateFlow
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail
import com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_detail.GetDetailTvShowsUseCase
import com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_popular.GetPopularTvShowsUseCase
import com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_recommendations.GetRecommendationsTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val popular_TvShows: GetPopularTvShowsUseCase,
    private val detail_TvShows: GetDetailTvShowsUseCase,
    private val recommendationsTvShows_useCase: GetRecommendationsTvShowsUseCase
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

    private val seriesIdStateFlow = SeriesIdStateFlow.getCurrentIdSeries()

    private val _getSeriesDetail = MutableLiveData<Status<TvShowsDetail>>()
    val seriesDetail: LiveData<Status<TvShowsDetail>> = _getSeriesDetail

    fun fetchSeriesDetail() {
        viewModelScope.launch {
            _getSeriesDetail.value = Status.Loading()
            try {
                seriesIdStateFlow.collectLatest { seriesId ->
                    detail_TvShows.invoke(seriesId).collect {
                        _getSeriesDetail.value = it
                    }
                }
            } catch (e: Exception) {
                _getSeriesDetail.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }

    private val _getRecommendationsTvShows = MutableLiveData<Status<List<TvShows>>>()
    val getRecommendationsTvShows: LiveData<Status<List<TvShows>>> get() = _getRecommendationsTvShows
    fun fetchRecommendationsTvShows() {
        viewModelScope.launch {
            _getRecommendationsTvShows.value = Status.Loading()
            try {
                seriesIdStateFlow.collectLatest { seriesId ->
                    recommendationsTvShows_useCase.invoke(seriesId, 1).collect {
                        _getRecommendationsTvShows.value = it
                    }
                }
            } catch (e: Exception) {
                _getRecommendationsTvShows.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }

}