package com.raflisalam.fakeneflix.domain.usecase.search

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {

    suspend operator fun invoke(moviesName: String): Flow<Status<List<Movies>>>
}