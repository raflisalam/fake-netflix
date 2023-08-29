package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import com.raflisalam.fakeneflix.domain.model.WatchlistMovies
import com.raflisalam.fakeneflix.domain.usecase.get_watchlist.WatchlistMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


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

    fun removeFromWatchlist(movieId: Int) {
        viewModelScope.launch {
            useCase.deleteFromWatchlist(movieId)
        }
    }

}