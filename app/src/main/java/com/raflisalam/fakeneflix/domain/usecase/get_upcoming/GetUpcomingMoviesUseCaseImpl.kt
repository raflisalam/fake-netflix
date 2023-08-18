package com.raflisalam.fakeneflix.domain.usecase.get_upcoming

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetUpcomingMoviesUseCase {
    override suspend fun invoke(page: Int): Flow<Status<List<Movies>>> {
        return repository.getUpcomingMovies(page)
    }

}