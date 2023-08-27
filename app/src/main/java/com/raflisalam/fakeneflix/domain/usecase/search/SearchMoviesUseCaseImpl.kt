package com.raflisalam.fakeneflix.domain.usecase.search

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): SearchMoviesUseCase {

    override suspend fun invoke(moviesName: String): Flow<Status<List<Movies>>> {
        return repository.getMoviesByName(moviesName)
    }
}