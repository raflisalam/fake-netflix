package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import com.raflisalam.fakeneflix.domain.model.WatchlistMovies
import com.raflisalam.fakeneflix.domain.usecase.favorite_movies.WatchlistMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


@HiltViewModel
class WatchlistMoviesViewModel @Inject constructor(
    private val useCase: WatchlistMoviesUseCase
): ViewModel() {

    val watchlistMovies: Flow<List<WatchlistMoviesEntity>> = useCase.execute()

    fun toggleWatchlistMovies(movie: WatchlistMovies) {
        viewModelScope.launch {
            useCase(movie)
        }
    }

}