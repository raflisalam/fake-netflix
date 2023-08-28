package com.raflisalam.fakeneflix.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.data.local.entity.FavoriteMovieEntity
import com.raflisalam.fakeneflix.domain.usecase.favorite_movies.FavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull


@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val useCase: FavoriteMoviesUseCase
): ViewModel() {

    fun toggleFavoriteStatus(movie: FavoriteMovieEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                useCase.removeFavoriteMovie(movie)
            } else {
                useCase.addFavoriteMovie(movie)
            }
        }
    }

     suspend fun isMovieFavorite(movieId: Int): Boolean {
         val favoriteMovies = useCase.getFavoriteMovies().first()
         return favoriteMovies.any { it.id == movieId}
    }

    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return useCase.getFavoriteMovies()
    }
}