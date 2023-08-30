package com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_recommendations

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationsMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetRecommendationsMoviesUseCase {
    override suspend fun invoke(movieId: Int, page: Int): Flow<Status<List<Movies>>> {
        return repository.getRecommendationsMovies(movieId, page)
    }
}