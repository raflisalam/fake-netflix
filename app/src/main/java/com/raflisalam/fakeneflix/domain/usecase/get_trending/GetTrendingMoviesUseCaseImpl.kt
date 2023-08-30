package com.raflisalam.fakeneflix.domain.usecase.get_trending

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetTrendingMoviesUseCase {
    
    override suspend fun invoke(timePeriod: String): Flow<Status<List<Movies>>> {
        return repository.getTrendingMovies(timePeriod)
    }
}