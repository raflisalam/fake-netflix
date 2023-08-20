package com.raflisalam.fakeneflix.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.PositionPageFlow
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.domain.usecase.background.ChangeBackgroundLayoutUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_upcoming.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val popular_useCase: GetPopularMoviesUseCase,
    private val nowPlaying_useCase: GetNowPlayingMoviesUseCase,
    private val upComing_useCase: GetUpcomingMoviesUseCase,
    private val background_useCase: ChangeBackgroundLayoutUseCase
): ViewModel() {

    private val _getPopularMovies = MutableLiveData<Status<List<Movies>>>()
    val getPopularMovies: LiveData<Status<List<Movies>>> get() = _getPopularMovies

    fun fetchPopularMovies(page: Int) {
        viewModelScope.launch {
            _getPopularMovies.value = Status.Loading()
            try {
                popular_useCase.invoke(page).collect {
                    _getPopularMovies.value = it
                }
            } catch (e: Exception) {
                _getPopularMovies.value = Status.Error("Failed to fetch popular movie list")
            }
        }
    }

    private val _getNowPlayingMovies = MutableLiveData<Status<List<Movies>>>()
    val getNowPlayingMovies: LiveData<Status<List<Movies>>> get() = _getNowPlayingMovies

    fun fetchNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            _getNowPlayingMovies.value = Status.Loading()
            try {
                nowPlaying_useCase.invoke(page).collect {
                    _getNowPlayingMovies.value = it

                }
            } catch (e: Exception) {
                _getNowPlayingMovies.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }

    private val _getUpcomingMovies = MutableLiveData<Status<List<Movies>>>()
    val getUpcomingMovies: LiveData<Status<List<Movies>>> get() = _getUpcomingMovies

    fun fetchUpcomingMovies(page: Int) {
        viewModelScope.launch {
            _getUpcomingMovies.value = Status.Loading()
            try {
                upComing_useCase.invoke(page).collect {
                    _getUpcomingMovies.value = it
                }
            } catch (e: Exception) {
                _getUpcomingMovies.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }

    private val _backgroundUrl = MutableLiveData<String>()
    val getBackgroundUrl: LiveData<String> get() = _backgroundUrl
    private val positionFlow = PositionPageFlow.getCurrentPageFlow()
    private val pageNameFlow = PositionPageFlow.getCurrentPageName()
    fun updateBackgroundLayout() {
        viewModelScope.launch {
            combine(pageNameFlow, positionFlow) { pageName, position ->
                Pair(pageName, position)
            }.collect { (pageName, position) ->
                when (pageName) {
                    "NowPlaying" -> {
                        val backgroundUrl = background_useCase.getBackgroundUrlNowPlaying(position)
                        _backgroundUrl.value = backgroundUrl
                    }
                    "Upcoming" -> {
                        val backgroundUrl = background_useCase.getBackgroundUrlUpcoming(position)
                        _backgroundUrl.value = backgroundUrl
                    }
                }
            }
        }
    }
}