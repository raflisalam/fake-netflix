package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.domain.model.credits.Cast
import com.raflisalam.fakeneflix.domain.model.movies.MovieDetails
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.GetDetailsMovieUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_credits_movie.GetCreditsMovieUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_recommendations.GetRecommendationsMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsMoviesViewModel @Inject constructor(
    private val getDetailsMovieUseCase: GetDetailsMovieUseCase,
    private val getCreditsActorMovieUseCase: GetCreditsMovieUseCaseImpl,
    private val recommendationsMovies_useCase: GetRecommendationsMoviesUseCase
): ViewModel() {

    private val moviesIdStateFlow = MoviesIdStateFlow.getCurrentIdMovies()

    private val _getMovieDetails = MutableLiveData<Status<MovieDetails>>()
    val movieDetails: LiveData<Status<MovieDetails>> = _getMovieDetails
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

    private val _getCreditsCastMovie = MutableLiveData<Status<List<Cast>>>()
    val getCreditsCastMovie: LiveData<Status<List<Cast>>> = _getCreditsCastMovie
    fun fetchCreditsActorMovies() {
        viewModelScope.launch {
            _getCreditsCastMovie.value = Status.Loading()
            try {
                moviesIdStateFlow.collectLatest { moviesId ->
                    getCreditsActorMovieUseCase.invoke(moviesId).collect {
                        _getCreditsCastMovie.value = it
                    }
                }
            } catch (e: Exception) {
                _getCreditsCastMovie.value = Status.Error("Failed to fetch credits actor list")
            }
        }
    }

    private val _getRecommendationsMovies = MutableLiveData<Status<List<Movies>>>()
    val getRecommendationsMovies: LiveData<Status<List<Movies>>> get() = _getRecommendationsMovies
    fun fetchRecommendationsMovies() {
        viewModelScope.launch {
            _getRecommendationsMovies.value = Status.Loading()
            try {
                moviesIdStateFlow.collectLatest { moviesId ->
                    recommendationsMovies_useCase.invoke(moviesId, 1).collect {
                        _getRecommendationsMovies.value = it
                    }
                }
            } catch (e: java.lang.Exception) {
                _getRecommendationsMovies.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }
}
