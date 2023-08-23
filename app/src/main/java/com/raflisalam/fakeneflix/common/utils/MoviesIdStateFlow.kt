package com.raflisalam.fakeneflix.common.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MoviesIdStateFlow {

    private val currentIdMovies = MutableStateFlow(0)
    fun getCurrentIdMovies(): StateFlow<Int> = currentIdMovies

    fun onMoviesSelected(moviesId: Int) {
        currentIdMovies.value = moviesId
    }
}