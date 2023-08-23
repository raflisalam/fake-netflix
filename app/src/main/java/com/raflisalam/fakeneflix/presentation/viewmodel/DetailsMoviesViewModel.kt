package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.domain.model.MovieDetails
import com.raflisalam.fakeneflix.domain.usecase.get_details_movie.GetDetailsMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsMoviesViewModel @Inject constructor(
    private val getDetailsMovieUseCase: GetDetailsMovieUseCase
): ViewModel() {

    private val _getMovieDetails = MutableLiveData<Status<MovieDetails>>()
    val movieDetails: LiveData<Status<MovieDetails>> = _getMovieDetails

    private val moviesIdStateFlow = MoviesIdStateFlow.getCurrentIdMovies()

    fun fetchMovieDetails() {
        viewModelScope.launch {
            _getMovieDetails.value = Status.Loading()
            try {
                moviesIdStateFlow.collectLatest { moviesId ->
                    getDetailsMovieUseCase.invoke(moviesId).collect {
                        _getMovieDetails.value = it
                    }
                }
            } catch (e: Exception) {
                _getMovieDetails.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }
}
