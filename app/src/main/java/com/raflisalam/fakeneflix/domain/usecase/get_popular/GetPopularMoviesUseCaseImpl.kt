package com.raflisalam.fakeneflix.domain.usecase.get_popular

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetPopularMoviesUseCase {

    override suspend fun invoke(page: Int): Flow<Status<List<Movies>>> {
        return repository.getPopularMovies(page)
    }
}